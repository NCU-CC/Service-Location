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

class NCULocationClient_UnitNameTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCULocationClient locationClient

    def setupSpec() {
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/unit/name/cc")
        ).respond(
                HttpResponse.response()
                        .withStatusCode( 200 )
                        .withHeaders(
                            new Header( "Content-Type", "application/json" )
                        )
                        .withBody('{"result":[{"lat":5.0,"lng":10.0,"url":"http://cc.com"}]}')
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
            Set<Unit> units = locationClient.getUnits( "cc" )
        then:
            def unitArr = units.toArray( new Unit[ units.size() ] )
        and:
            unitArr[0].getName() == null
            unitArr[0].getLatitude()  == 5.0
            unitArr[0].getLongitude() == 10.0
            unitArr[0].getUrl() == "http://cc.com"
    }


}
