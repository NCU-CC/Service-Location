package tw.edu.ncu.cc.location.client.jersey

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.TestServerSetting
import tw.edu.ncu.cc.location.client.tool.config.LocationConfig
import tw.edu.ncu.cc.location.data.person.Person

class NCUSynLocationClient_PersonNameTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCUSynLocationClient locationClient

    def setupSpec() {
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/person/name/jason")
        ).respond(
                HttpResponse.response()
                        .withStatusCode( 200 )
                        .withHeaders(
                            new Header( "Content-Type", "application/json" )
                        )
                        .withBody(
                        '''
                        {
                            "result" : [
                                {
                                    "chineseName" : "jason",
                                    "englishName" : "jasonChiu",
                                    "title" : "teacher",
                                    "primaryUnit" : {
                                        "unitCode"   : "A100",
                                        "chineseName": "cname1",
                                        "englishName":  null,
                                        "shortName"  : "sname1",
                                        "fullName"   : "fname1",
                                        "url" : null,
                                        "location" : null
                                    },
                                    "secondaryUnit" : {
                                        "unitCode"   : "A102",
                                        "chineseName": "cname2",
                                        "englishName":  null,
                                        "shortName"  : "sname2",
                                        "fullName"   : "fname2",
                                        "url" : null,
                                        "location" : null
                                    },
                                    "officePhone" : null
                                }
                            ]
                        }
                        '''
                )
        )
    }

    def cleanupSpec() {
        mockServer.stop()
    }

    def setup() {
        locationClient = new NCUSynLocationClient( Mock( LocationConfig ){
            getServerAddress() >> "http://127.0.0.1:" + TestServerSetting.port
        } )
    }

    def "it can fetch places information from server"() {
        when:
            Set<Person> people = locationClient.getPeople( "jason" )
        then:
            def unitArr = people.toArray( new Person[ people.size() ] )
        and:
            unitArr[0].getChineseName() == "jason"
            unitArr[0].getEnglishName() == "jasonChiu"
            unitArr[0].getTitle()       == "teacher"
            unitArr[0].getOfficePhone() == null
        and:
            def unit1 = unitArr[0].getPrimaryUnit()
            unit1.getUnitCode()    == "A100"
            unit1.getChineseName() == "cname1"
        and:
            def unit2 = unitArr[0].getSecondaryUnit()
            unit2.getUnitCode()    == "A102"
            unit2.getChineseName() == "cname2"
    }


}
