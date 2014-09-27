package tw.edu.ncu.cc.location.client.core

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.TestServerSetting
import tw.edu.ncu.cc.location.client.core.abstracts.LocationConfig
import tw.edu.ncu.cc.location.client.data.Place
import tw.edu.ncu.cc.location.data.place.PlaceType


class NCULocationClient_PlaceNameTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCULocationClient locationClient

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
                        .withBody('{"result":[{"lat":1.0,"lng":2.0,"picName":"123.png","type":"SCENE"}]}')
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
            Set<Place> places = locationClient.getPlaces( "home" )
        then:
            def placeArr = places.toArray( new Place[ places.size() ] )
        and:
            placeArr[0].getName() == null
            placeArr[0].getType() == PlaceType.SCENE
            placeArr[0].getLatitude()  == 1.0
            placeArr[0].getLongitude() == 2.0
            placeArr[0].getPictureName() == "123.png"
    }

}
