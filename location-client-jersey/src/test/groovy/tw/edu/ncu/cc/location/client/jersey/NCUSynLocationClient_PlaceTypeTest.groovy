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


class NCUSynLocationClient_PlaceTypeTest extends Specification {

    @Shared @ClassRule
    MockServerResource serverResource = new MockServerResource()

    @Rule
    LocationClientResource clientResource = new LocationClientResource()

    def setupSpec() {
        serverResource.getMockServer().when(
                HttpRequest.request()
                        .withMethod( "GET" )
                        .withPath( "/place/type/OTHER" )
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
                                    "pictureName":"tree.jpg",
                                    "type":"OTHER",
                                    "location":{
                                        "lat":2,
                                        "lng":4
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
            def places = locationClient.getPlaces( PlaceType.OTHER )
        then:
            places.contains(
                    new Place(
                            chineseName: "home",
                            englishName: "home",
                            pictureName: "tree.jpg",
                            type: PlaceType.OTHER,
                            location: new Location(
                                    lat: 2,
                                    lng: 4
                            )
                    )
            )
    }

}
