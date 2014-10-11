package tw.edu.ncu.cc.location.client.jersey

import org.junit.ClassRule
import org.junit.Rule
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.resource.LocationClientResource
import tw.edu.ncu.cc.location.client.resource.MockServerResource
import tw.edu.ncu.cc.location.data.location.Location
import tw.edu.ncu.cc.location.data.place.Place
import tw.edu.ncu.cc.location.data.place.PlaceType

class NCUSynLocationClient_PlaceNameTest extends Specification {

    @Shared @ClassRule
    MockServerResource serverResource = new MockServerResource()

    @Rule
    LocationClientResource clientResource = new LocationClientResource()

    def setupSpec() {
        serverResource.getMockServer().when(
                HttpRequest.request()
                        .withMethod( "GET" )
                        .withPath( "/place/name/home" )
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
                                    "chineseName":"home",
                                    "englishName":"home",
                                    "pictureName":"123.png",
                                    "type":"SCENE",
                                    "location":{
                                        "lat":1,
                                        "lng":2
                                    }
                                }
                            ]
                        }
                        '''
                )
        )
    }

    def "it can fetch places information from server"() {
        given:
            def locationClient = clientResource.getClient()
        when:
            def places = locationClient.getPlaces( "home" )
        then:
            places.contains(
                    new Place(
                            chineseName: "home",
                            englishName: "home",
                            pictureName: "123.png",
                            type: PlaceType.SCENE,
                            location: new Location(
                                    lat: 1,
                                    lng: 2
                            )
                    )
            )
    }

}
