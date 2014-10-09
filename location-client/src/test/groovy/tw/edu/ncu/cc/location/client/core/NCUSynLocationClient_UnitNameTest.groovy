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

class NCUSynLocationClient_UnitNameTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCUSynLocationClient locationClient

    def setupSpec() {
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/unit/name/ncucc")
        ).respond(
                HttpResponse.response()
                        .withStatusCode( 200 )
                        .withHeaders(
                            new Header( "Content-Type", "application/json" )
                        )
                        .withBody('{"result":[{' +
                        '"unitCode":"code",' +
                        '"chineseName":"cc",' +
                        '"englishName":"cc",' +
                        '"shortName":"c",' +
                        '"fullName":"ncucc",' +
                        '"url":"gamer.com",' +
                        '"location":{"lat":5,"lng":10}' +
                        '}]}')
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
            Set<Unit> units = locationClient.getUnits( "ncucc" )
        then:
            def unitArr = units.toArray( new Unit[ units.size() ] )
        and:
            unitArr[0].getUnitCode()    == "code"
            unitArr[0].getChineseName() == "cc"
            unitArr[0].getEnglishName() == "cc"
            unitArr[0].getShortName()   == "c"
            unitArr[0].getFullName()    == "ncucc"
            unitArr[0].getUrl() == "gamer.com"
            unitArr[0].getLocation().getLat() == 5
            unitArr[0].getLocation().getLng() == 10
    }


}
