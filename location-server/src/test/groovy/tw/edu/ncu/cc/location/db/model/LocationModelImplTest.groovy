package tw.edu.ncu.cc.location.db.model

import org.hibernate.PropertyValueException
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Location
import tw.edu.ncu.cc.location.db.data.LocationType
import tw.edu.ncu.cc.location.factory.HibernateUtilFactory


class LocationModelImplTest extends Specification {

    private @Shared HibernateUtil hibernateUtil
    private LocationModelImpl locationModel

    def setupSpec() {
        hibernateUtil = new HibernateUtilFactory().provide()
    }

    def setup() {
        locationModel = new LocationModelImpl()
        locationModel.setSession( hibernateUtil.currentSession() )
    }

    def cleanup() {
        hibernateUtil.closeSession()
    }

    def "persisted data must have some values exist"() {
        given:
            Location location = new Location()
            location.setType( LocationType.SCENE )
            location.setLongitude( 10 )
            location.setLatitude ( 10 )
        when:
            locationModel.persistLocation( location )
        then:
            notThrown( PropertyValueException )
    }

    def "thrown exception when some fields is null"() {
        given:
            Location location = new Location()
        when:
            locationModel.persistLocation( location )
        then:
            thrown( PropertyValueException )
    }

    def "it can get locaiton by id"() {
        given:
            Location location = new Location( LocationType.SCENE, 10, 20 )
        when:
            locationModel.persistLocation( location )
        then:
            locationModel.getLocation( 2 ) == location
    }

    def "it can get location by location name"() {
        given:
            Location location1 = new Location( LocationType.RESTAURANT , 1, 1 )
            Location location2 = new Location( LocationType.RESTAURANT , 1, 1 )
            Location location3 = new Location( LocationType.WHEELCHAIR_RAMP, 1, 1 )
        when:
            locationModel.persistLocation( location1, location2, location3 )
        and:
            def locations = locationModel.getLocations( LocationType.RESTAURANT )
        then:
            locations.contains( location1 )
            locations.contains( location2 )
            ! locations.contains( location3 )

    }


}
