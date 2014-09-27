package tw.edu.ncu.cc.location.server.service

import com.jayway.restassured.RestAssured
import groovy.json.JsonSlurper
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.db.HibernateUtil
import tw.edu.ncu.cc.location.server.db.data.Place
import tw.edu.ncu.cc.location.server.db.model.PlaceModelImpl
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel
import tw.edu.ncu.cc.location.server.factory.HibernateUtilFactory

class PlaceServiceIntegrationTest extends Specification {

    def setupSpec() {
        RestAssuredTestConfiguer.configure()
        initData();
    }

    private static void initData() {
        HibernateUtil hibernateUtil = new HibernateUtilFactory().provide()
        PlaceModel placeModel = new PlaceModelImpl()
        placeModel.setSession( hibernateUtil.currentSession() )
        placeModel.persistPlace(
            new Place( PlaceType.SCENE, 1.0, 1.0, "scene1" ),
            new Place( PlaceType.SCENE, 2.0, 2.0, "scene1" ),
            new Place( PlaceType.EMERGENCY_TEL, 3.0, 3.0, "tel1" )
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
                        "name": "tel1",
                        "lng" : 3.0,
                        "lat" : 3.0,
                        "picName" : null
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
                        "name": "scene1",
                        "lng" : 1.0,
                        "lat" : 1.0,
                        "picName" : null
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "name": "scene1",
                        "lng" : 2.0,
                        "lat" : 2.0,
                        "picName" : null
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
                    RestAssured.get( "/place/name/scene1" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "type": "SCENE",
                        "lng" : 1.0,
                        "lat" : 1.0,
                        "picName" : null
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "type": "SCENE",
                        "lng" : 2.0,
                        "lat" : 2.0,
                        "picName" : null
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
