package tw.edu.ncu.cc.location.client.jersey

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.TestServerSetting
import tw.edu.ncu.cc.location.client.tool.config.LocationConfig
import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.data.keyword.WordType

class NCUSynLocationClient_KeywordTest extends Specification {

    @Shared private ClientAndServer  mockServer = ClientAndServer.startClientAndServer( TestServerSetting.port )

    private NCUSynLocationClient locationClient

    def setupSpec() {
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/keyword/CC")
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

    def cleanupSpec() {
        mockServer.stop()
    }

    def setup() {
        locationClient = new NCUSynLocationClient( Mock( LocationConfig ){
            getServerAddress() >> "http://127.0.0.1:" + TestServerSetting.port
        } )
    }

    def "it can fetch keyword information from server"() {
        when:
            Set<Word> words = locationClient.getWords( "CC" )
        then:
            words.contains( new Word( "computer center", WordType.UNIT ) )
    }

}
