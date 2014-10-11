package tw.edu.ncu.cc.location.server.service

import groovy.json.JsonSlurper
import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.server.db.data.PersonEntity
import tw.edu.ncu.cc.location.server.db.data.UnitEntity
import tw.edu.ncu.cc.location.server.db.model.PersonModelImpl
import tw.edu.ncu.cc.location.server.db.model.UnitModelImpl
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel
import tw.edu.ncu.cc.location.server.db.model.abstracts.UnitModel
import tw.edu.ncu.cc.location.server.resource.HttpResource
import tw.edu.ncu.cc.location.server.resource.PersistSessionResource
import tw.edu.ncu.cc.location.server.resource.SessionResource

import static HttpResource.requestJSON
import static HttpResource.requestString

class PersonServiceIntegrationTest extends Specification {

    @Shared @ClassRule
    HttpResource httpResource = new HttpResource()

    @Shared @ClassRule
    SessionResource sessionResource = new PersistSessionResource()

    def setupSpec() {
        PersonModel personModel = new PersonModelImpl()
        personModel.setSession( sessionResource.getSession() )

        UnitModel unitModel = new UnitModelImpl()
        unitModel.setSession( sessionResource.getSession() )

        UnitEntity unit1 = new UnitEntity( "upcode1", "cname1", "sname1", "fname1" )
        UnitEntity unit2 = new UnitEntity( "upcode2", "cname2", "sname2", "fname2" )
        unitModel.persistUnits( unit1, unit2 )

        personModel.persistPeople(
                new PersonEntity( "code", "name1", "title", unit1, unit2 )
        )
    }

    def "server can return all units of a person by person name"() {
        when:
            def response = requestJSON( "/person/name/name1" )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "chineseName" : "name1",
                        "englishName" : null,
                        "title" : "title",
                        "primaryUnit" : {
                            "unitCode"   : "upcode1",
                            "chineseName": "cname1",
                            "englishName":  null,
                            "shortName"  : "sname1",
                            "fullName"   : "fname1",
                            "url" : null,
                            "location" : null
                        },
                        "secondaryUnit" : {
                            "unitCode"   : "upcode2",
                            "chineseName": "cname2",
                            "englishName":  null,
                            "shortName"  : "sname2",
                            "fullName"   : "fname2",
                            "url" : null,
                            "location" : null
                        },
                        "officePhone" : null
                    }
                    '''
            ) )
    }

    def "server can return all units of a person by person name 2"() {
        when:
            def response = requestString( "/person/name/personNotExist" )
        then:
            response == '{"result":null}'
    }

}
