package tw.edu.ncu.cc.location.server.db.model

import org.hibernate.PropertyValueException
import spock.lang.Shared
import spock.lang.Specification
import tool.HibernateUtilTestFactory
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.db.HibernateUtil
import tw.edu.ncu.cc.location.server.db.data.Place

class PlaceModelImplTest extends Specification {

    private @Shared HibernateUtil hibernateUtil
    private PlaceModelImpl placeModel

    def setupSpec() {
        hibernateUtil = new HibernateUtilTestFactory().provide()
    }

    def setup() {
        placeModel = new PlaceModelImpl()
        placeModel.setSession( hibernateUtil.currentSession() )
    }

    def cleanup() {
        hibernateUtil.closeSession()
    }

    def "persisted data must have some values exist"() {
        given:
            Place place = new Place()
            place.setType( PlaceType.SCENE )
            place.setLongitude( 10 )
            place.setLatitude ( 10 )
        when:
            placeModel.persistPlace( place )
        then:
            notThrown( PropertyValueException )
    }

    def "thrown exception when some fields is null"() {
        given:
            Place place = new Place()
        when:
            placeModel.persistPlace( place )
        then:
            thrown( PropertyValueException )
    }

    def "it can get place by id"() {
        given:
            Place place = new Place( PlaceType.SCENE, 10, 20 )
        when:
            placeModel.persistPlace( place )
        then:
            placeModel.getPlace( 2 ) == place
    }

    def "it can get place by place name"() {
        given:
            Place place1 = new Place( PlaceType.SCENE, 1, 1, "toilet" )
            Place place2 = new Place( PlaceType.SCENE, 1, 1, "toilet" )
            Place place3 = new Place( PlaceType.SCENE, 1, 1, "school" )
        when:
            placeModel.persistPlace( place1, place2, place3 )
        and:
            def places = placeModel.getPlaces( "toilet" )
        then:
            places.contains( place1 )
            places.contains( place2 )
            ! places.contains( place3 )
    }

    def "it can get place by place type"() {
        given:
            Place place1 = new Place( PlaceType.RESTAURANT, 1, 1 )
            Place place2 = new Place( PlaceType.RESTAURANT, 1, 1 )
            Place place3 = new Place( PlaceType.WHEELCHAIR_RAMP, 1, 1 )
        when:
            placeModel.persistPlace( place1, place2, place3 )
        and:
            def places = placeModel.getPlaces( PlaceType.RESTAURANT )
        then:
            places.contains( place1 )
            places.contains( place2 )
            ! places.contains( place3 )
    }


}
