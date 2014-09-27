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


class NCULocationClient_PlaceTypeTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCULocationClient locationClient

    def setupSpec() {
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/place/type/SCENE")
        ).respond(
                HttpResponse.response()
                        .withStatusCode( 200 )
                        .withHeaders(
                            new Header( "Content-Type", "application/json" )
                        )
                        .withBody('{"result":[{"name":"tree","lat":3.3,"lng":6.6,"picName":"tree.jpg"}]}')
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
            Set<Place> places = locationClient.getPlaces( PlaceType.SCENE )
        then:
            def placeArr = places.toArray( new Place[ places.size() ] )
        and:
            placeArr[0].getName() == "tree"
            placeArr[0].getType() == null
            placeArr[0].getLatitude()  == 3.3
            placeArr[0].getLongitude() == 6.6
            placeArr[0].getPictureName() == "tree.jpg"
    }

}
