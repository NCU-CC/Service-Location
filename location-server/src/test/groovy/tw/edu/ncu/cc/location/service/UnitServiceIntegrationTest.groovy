package tw.edu.ncu.cc.location.service

import com.jayway.restassured.RestAssured
import groovy.json.JsonSlurper
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Unit
import tw.edu.ncu.cc.location.db.model.UnitModelImpl
import tw.edu.ncu.cc.location.factory.HibernateUtilFactory


class UnitServiceIntegrationTest extends Specification {

    def setupSpec() {
        RestAssuredTestConfiguer.configure()
        initData();
    }

    private static void initData() {
        HibernateUtil hibernateUtil = new HibernateUtilFactory().provide()
        UnitModelImpl unitModel     = new UnitModelImpl()
        unitModel.setSession( hibernateUtil.currentSession() )
        unitModel.persistUnits(
                new Unit( "integration1", 1.0, 1.0 ),
                new Unit( "integration2", 2.0, 2.0 ),
                new Unit( "integration3", 3.0, 3.0 ),
                new Unit( "integration3", 4.0, 4.0 )
        )
        hibernateUtil.closeSession()
    }

    def "server will return all units by name 1"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/unit/name/integration1" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "lng" : 1.0,
                        "lat" : 1.0,
                        "url" : null
                    }
                    '''
            ) )
    }

    def "server will return all units by name 2"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/unit/name/integration3" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "lng" : 3.0,
                        "lat" : 3.0,
                        "url" : null
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "lng" : 4.0,
                        "lat" : 4.0,
                        "url" : null
                    }
                    '''
            ) )
    }


}
