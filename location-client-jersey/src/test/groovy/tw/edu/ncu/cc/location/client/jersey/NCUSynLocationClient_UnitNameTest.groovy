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
import tw.edu.ncu.cc.location.data.unit.Unit

class NCUSynLocationClient_UnitNameTest extends Specification {

    @Shared @ClassRule
    MockServerResource serverResource = new MockServerResource()

    @Rule
    LocationClientResource clientResource = new LocationClientResource()

    def setupSpec() {
        serverResource.getMockServer().when(
                HttpRequest.request()
                        .withMethod( "GET" )
                        .withPath( "/unit/name/ncucc" )
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
                                    "unitCode":"code",
                                    "chineseName":"cc",
                                    "englishName":"cc",
                                    "shortName":"c",
                                    "fullName":"ncucc",
                                    "url":"gamer.com",
                                    "location":{
                                        "lat":5,
                                        "lng":10
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
            def units = locationClient.getUnits( "ncucc" )
        then:
            units.contains(
                new Unit(
                        unitCode: "code",
                        chineseName: "cc",
                        englishName: "cc",
                        shortName: "c",
                        fullName: "ncucc",
                        url: "gamer.com",
                        location: new Location(
                                lat: 5,
                                lng: 10
                        )
                )
            )
    }


}
