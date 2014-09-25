package tw.edu.ncu.cc.location.service

import com.jayway.restassured.RestAssured
import groovy.json.JsonSlurper
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Location
import tw.edu.ncu.cc.location.db.data.LocationType
import tw.edu.ncu.cc.location.db.model.LocationModelImpl
import tw.edu.ncu.cc.location.db.model.abstracts.LocationModel
import tw.edu.ncu.cc.location.factory.HibernateUtilFactory


class LocationServiceIntegrationTest extends Specification {

    def setupSpec() {
        RestAssuredTestConfiguer.configure()
        initData();
    }

    private static void initData() {
        HibernateUtil hibernateUtil = new HibernateUtilFactory().provide()
        LocationModel locationModel = new LocationModelImpl()
        locationModel.setSession( hibernateUtil.currentSession() )
        locationModel.persistLocation(
            new Location( LocationType.SCENE, 1.0, 1.0, "scene1" ),
            new Location( LocationType.SCENE, 2.0, 2.0, "scene1" ),
            new Location( LocationType.EMERGENCY_TEL, 3.0, 3.0, "tel1" )
        )
        hibernateUtil.closeSession()
    }

    def "server will return all units by type 1"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/location/type/EMERGENCY_TEL" ).asString()
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

    def "server will return all units by type 2"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/location/type/SCENE" ).asString()
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


    def "server will return all units by name"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/location/name/scene1" ).asString()
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

}
