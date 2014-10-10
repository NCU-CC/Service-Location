package tw.edu.ncu.cc.location.client.core

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.TestServerSetting
import tw.edu.ncu.cc.location.client.tool.config.LocationConfig
import tw.edu.ncu.cc.location.data.place.Place
import tw.edu.ncu.cc.location.data.place.PlaceType

class NCUSynLocationClient_PlaceNameTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCUSynLocationClient locationClient

    def setupSpec() {
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/place/name/home")
        ).respond(
                HttpResponse.response()
                        .withStatusCode( 200 )
                        .withHeaders(
                            new Header( "Content-Type", "application/json" )
                        )
                        .withBody('{"result":[{' +
                        '"chineseName":"home",' +
                        '"englishName":"home",' +
                        '"pictureName":"123.png",' +
                        '"type":"SCENE",' +
                        '"location":{"lat":1,"lng":2}' +
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
            Set<Place> places = locationClient.getPlaces( "home" )
        then:
            def placeArr = places.toArray( new Place[ places.size() ] )
        and:
            placeArr[0].getChineseName() == "home"
            placeArr[0].getEnglishName() == "home"
            placeArr[0].getPictureName() == "123.png"
            placeArr[0].getType() == PlaceType.SCENE
            placeArr[0].getLocation().getLat() == 1
            placeArr[0].getLocation().getLng() == 2
    }

}
