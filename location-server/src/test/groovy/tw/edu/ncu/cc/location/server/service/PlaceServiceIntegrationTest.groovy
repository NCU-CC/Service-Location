package tw.edu.ncu.cc.location.server.service

import com.vividsolutions.jts.geom.Coordinate
import com.vividsolutions.jts.geom.GeometryFactory
import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity
import tw.edu.ncu.cc.location.server.db.model.PlaceModel
import tw.edu.ncu.cc.location.server.db.model.impl.PlaceModelImpl
import tw.edu.ncu.cc.location.server.resource.HttpResource
import tw.edu.ncu.cc.location.server.resource.PersistSessionResource
import tw.edu.ncu.cc.location.server.resource.SessionResource

import static tw.edu.ncu.cc.location.server.resource.HttpResource.*

class PlaceServiceIntegrationTest extends Specification {

    @Shared @ClassRule
    HttpResource httpResource = new HttpResource()

    @Shared @ClassRule
    SessionResource sessionResource = new PersistSessionResource()

    def setupSpec() {
        GeometryFactory geometryFactory = new GeometryFactory()

        PlaceModel placeModel = new PlaceModelImpl()
        placeModel.setSession( sessionResource.getSession() )
        placeModel.persistPlace(
                new PlaceEntity( PlaceType.OTHER, "scene2", geometryFactory.createPoint( new Coordinate( 1.0, 1.0 ) )),
                new PlaceEntity( PlaceType.OTHER, "scene2", geometryFactory.createPoint( new Coordinate( 2.0, 2.0 ) )),
                new PlaceEntity( PlaceType.EMERGENCY, "tel1" )
        )
    }

    def "server can return all places by type 1"() {
        when:
            def response = requestJSON( "/place/type/EMERGENCY" )
        then:
            response.result.contains( JSON(
                    '''
                    {
                        "chineseName": "tel1",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "EMERGENCY",
                        "location" : null
                    }
                    '''
            ) )
    }

    def "server can return all places by type 2"() {
        when:
            def response = requestJSON( "/place/type/OTHER" )
        then:
            response.result.contains( JSON(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "OTHER",
                        "location" : {
                            "lat":1.0,
                            "lng":1.0
                        }
                    }
                    '''
            ) )
        and:
            response.result.contains( JSON(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "OTHER",
                        "location" : {
                            "lat":2.0,
                            "lng":2.0
                        }
                    }
                    '''
            ))
    }

    def "server can return all places by type 3"() {
        when:
            def response = requestString( "/place/type/TypeNotExist" )
        then:
            response == '{"result":null}'
    }

    def "server can return all units by name 1"() {
        when:
            def response = requestJSON( "/place/name/scene2" )
        then:
            response.result.contains( JSON(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "OTHER",
                        "location" : {
                            "lat":1.0,
                            "lng":1.0
                        }
                    }
                    '''
            ) )
        and:
            response.result.contains( JSON(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "OTHER",
                        "location" : {
                            "lat":2.0,
                            "lng":2.0
                        }
                    }
                    '''
            ) )
    }

    def "server can return all places by name 2"() {
        when:
            def response = requestString( "/place/name/NameNotExist" )
        then:
            response == '{"result":null}'
    }

}
