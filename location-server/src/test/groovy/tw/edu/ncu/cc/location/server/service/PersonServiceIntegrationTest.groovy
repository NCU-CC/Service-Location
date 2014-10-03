package tw.edu.ncu.cc.location.server.service

import com.jayway.restassured.RestAssured
import groovy.json.JsonSlurper
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.server.db.HibernateUtil
import tw.edu.ncu.cc.location.server.db.data.PersonEntity
import tw.edu.ncu.cc.location.server.db.data.UnitEntity
import tw.edu.ncu.cc.location.server.db.model.PersonModelImpl
import tw.edu.ncu.cc.location.server.db.model.UnitModelImpl
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel
import tw.edu.ncu.cc.location.server.db.model.abstracts.UnitModel
import tw.edu.ncu.cc.location.server.factory.HibernateUtilFactory

class PersonServiceIntegrationTest extends Specification {

    def setupSpec() {
        RestAssuredTestConfiguer.configure()
        initData();
    }

    private static void initData() {
        HibernateUtil hibernateUtil = new HibernateUtilFactory().provide()

        PersonModel personModel = new PersonModelImpl()
        personModel.setSession( hibernateUtil.currentSession() )

        UnitModel unitModel = new UnitModelImpl()
        unitModel.setSession( hibernateUtil.currentSession() )

        UnitEntity unit1 = new UnitEntity( "upcode1", "cname1", "sname1", "fname1" )
        UnitEntity unit2 = new UnitEntity( "upcode2", "cname2", "sname2", "fname2" )
        unitModel.persistUnits( unit1, unit2 )

        personModel.persistPersons(
            new PersonEntity( "code", "name1", "title", unit1, unit2 )
        )

        hibernateUtil.closeSession()
    }

    def "server can return all units of a person by person name"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/person/name/name1" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                     {
                        "unitCode"   : "upcode1",
                        "chineseName": "cname1",
                        "englishName":  null,
                        "shortName"  : "sname1",
                        "fullName"   : "fname1",
                        "url" : null,
                        "location" : null
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                     {
                        "unitCode"   : "upcode2",
                        "chineseName": "cname2",
                        "englishName":  null,
                        "shortName"  : "sname2",
                        "fullName"   : "fname2",
                        "url" : null,
                        "location" : null
                    }
                    '''
            ) )
    }

    def "server can return all units of a person by person name 2"() {
        when:
            def response = RestAssured.get( "/person/name/personNotExist" ).asString()
        then:
            response == '{"result":null}'
    }

}
