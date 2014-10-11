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
import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.data.keyword.WordType

class NCUSynLocationClient_KeywordTest extends Specification {

    @Shared @ClassRule
    MockServerResource serverResource = new MockServerResource()

    @Rule
    LocationClientResource clientResource = new LocationClientResource()

    def setupSpec() {
        serverResource.getMockServer().when(
                HttpRequest.request()
                        .withMethod( "GET" )
                        .withPath( "/keyword/CC" )
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
                                    "word" : "computer center",
                                    "type" : "UNIT"
                                }
                            ]
                        }
                        '''
                )
        )
    }

    def "it can fetch keyword information from server"() {
        given:
            def locationClient = clientResource.getClient()
        when:
            def words = locationClient.getWords( "CC" )
        then:
            words.contains(
                    new Word(
                            word: "computer center",
                            type: WordType.UNIT
                    )
            )
    }

}
