package tw.edu.ncu.cc.location.service

import com.jayway.restassured.RestAssured
import groovy.json.JsonSlurper
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Person
import tw.edu.ncu.cc.location.db.data.Unit
import tw.edu.ncu.cc.location.db.model.PersonModelImpl
import tw.edu.ncu.cc.location.db.model.UnitModelImpl
import tw.edu.ncu.cc.location.db.model.abstracts.PersonModel
import tw.edu.ncu.cc.location.db.model.abstracts.UnitModel
import tw.edu.ncu.cc.location.factory.HibernateUtilFactory


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

        Unit unit1 = new Unit( "home"  , 1.0, 1.0 )
        Unit unit2 = new Unit( "school", 2.0, 2.0 )
        Person person = new Person( "jason", unit1, unit2 )

        unitModel.persistUnits( unit1, unit2 )
        personModel.persistPersons( person )

        hibernateUtil.closeSession()
    }

    def "server will return all units of a person by person name"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/person/name/jason" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "name": "home",
                        "lng" : 1.0,
                        "lat" : 1.0,
                        "url" : null
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "name": "school",
                        "lng" : 2.0,
                        "lat" : 2.0,
                        "url" : null
                    }
                    '''
            ) )
    }
}
