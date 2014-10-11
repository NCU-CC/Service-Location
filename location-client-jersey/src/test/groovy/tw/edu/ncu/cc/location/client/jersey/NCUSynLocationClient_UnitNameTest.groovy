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
            Set<Unit> units = locationClient.getUnits( "ncucc" )
        then:
            def unitArr = units.toArray( new Unit[units.size()] )
        and:
            unitArr[ 0 ].getUnitCode() == "code"
            unitArr[ 0 ].getChineseName() == "cc"
            unitArr[ 0 ].getEnglishName() == "cc"
            unitArr[ 0 ].getShortName() == "c"
            unitArr[ 0 ].getFullName() == "ncucc"
            unitArr[ 0 ].getUrl() == "gamer.com"
            unitArr[ 0 ].getLocation().getLat() == 5
            unitArr[ 0 ].getLocation().getLng() == 10
    }


}
