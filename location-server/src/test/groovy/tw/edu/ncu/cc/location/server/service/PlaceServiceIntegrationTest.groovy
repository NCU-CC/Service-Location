package tw.edu.ncu.cc.location.server.service

import com.jayway.restassured.RestAssured
import com.vividsolutions.jts.geom.Coordinate
import com.vividsolutions.jts.geom.GeometryFactory
import groovy.json.JsonSlurper
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.db.HibernateUtil
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity
import tw.edu.ncu.cc.location.server.db.model.PlaceModelImpl
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel
import tw.edu.ncu.cc.location.server.factory.HibernateUtilFactory

class PlaceServiceIntegrationTest extends Specification {

    def setupSpec() {
        RestAssuredTestConfiguer.configure()
        initData();
    }

    private static void initData() {
        GeometryFactory geometryFactory = new GeometryFactory()
        HibernateUtil hibernateUtil = new HibernateUtilFactory().provide()

        PlaceModel placeModel = new PlaceModelImpl()
        placeModel.setSession( hibernateUtil.currentSession() )
        placeModel.persistPlace(
            new PlaceEntity( PlaceType.SCENE, "scene2", geometryFactory.createPoint( new Coordinate( 1.0, 1.0 ) )),
            new PlaceEntity( PlaceType.SCENE, "scene2", geometryFactory.createPoint( new Coordinate( 2.0, 2.0 ) )),
            new PlaceEntity( PlaceType.EMERGENCY_TEL, "tel1" )
        )

        hibernateUtil.closeSession()
    }

    def "server can return all places by type 1"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/place/type/EMERGENCY_TEL" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "chineseName": "tel1",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "EMERGENCY_TEL",
                        "location" : null
                    }
                    '''
            ) )
    }

    def "server can return all places by type 2"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/place/type/SCENE" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "SCENE",
                        "location" : {
                            "lat":1.0,
                            "lng":1.0
                        }
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "SCENE",
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
            def response = RestAssured.get( "/place/type/TypeNotExist" ).asString()
        then:
            response == '{"result":null}'
    }

    def "server can return all units by name 1"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/place/name/scene2" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "SCENE",
                        "location" : {
                            "lat":1.0,
                            "lng":1.0
                        }
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "chineseName": "scene2",
                        "englishName" : null,
                        "pictureName" : null,
                        "type" : "SCENE",
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
            def response = RestAssured.get( "/place/name/NameNotExist" ).asString()
        then:
            response == '{"result":null}'
    }

}
