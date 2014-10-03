package tw.edu.ncu.cc.location.client.core

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.TestServerSetting
import tw.edu.ncu.cc.location.client.core.abstracts.LocationConfig
import tw.edu.ncu.cc.location.data.unit.Unit

class NCULocationClient_PersonUnitTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCULocationClient locationClient

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
                        .withBody('{"result":[{' +
                        '"unitCode":"code",' +
                        '"chineseName":"jason",' +
                        '"englishName":"jason",' +
                        '"shortName":"jj",' +
                        '"fullName":"jasonChiu",' +
                        '"url":"gamer.com",' +
                        '"location":{"lat":1,"lng":2}' +
                        '}]}')
        )
    }

    def cleanupSpec() {
        mockServer.stop()
    }

    def setup() {
        locationClient = new NCULocationClient( Mock( LocationConfig ){
            getServerAddress() >> "http://127.0.0.1:" + TestServerSetting.port
        } )
    }

    def "it can fetch places information from server"() {
        when:
            Set<Unit> units = locationClient.getPersonUnits( "jason" )
        then:
            def unitArr = units.toArray( new Unit[ units.size() ] )
        and:
            unitArr[0].getUnitCode()    == "code"
            unitArr[0].getChineseName() == "jason"
            unitArr[0].getEnglishName() == "jason"
            unitArr[0].getShortName()   == "jj"
            unitArr[0].getFullName()    == "jasonChiu"
            unitArr[0].getUrl() == "gamer.com"
            unitArr[0].getLocation().getLat() == 1
            unitArr[0].getLocation().getLng() == 2
    }


}
