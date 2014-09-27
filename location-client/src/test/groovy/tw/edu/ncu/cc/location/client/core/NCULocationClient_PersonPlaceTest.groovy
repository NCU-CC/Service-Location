package tw.edu.ncu.cc.location.client.core

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.TestServerSetting
import tw.edu.ncu.cc.location.client.core.abstracts.LocationConfig
import tw.edu.ncu.cc.location.client.data.Unit

class NCULocationClient_PersonPlaceTest extends Specification {

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
                        .withBody('{"result":[{"name":"cc","lat":2.0,"lng":4.0,"url":"http://cc.com"}]}')
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
            unitArr[0].getName() == "cc"
            unitArr[0].getLatitude()  == 2.0
            unitArr[0].getLongitude() == 4.0
            unitArr[0].getUrl() == "http://cc.com"
    }


}
