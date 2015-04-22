package tw.edu.ncu.cc.location.server.web.api.v3

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.apiToken

class WordControllerTest extends IntegrationSpecification {

    def "it should provide api token to use api"() {
        setup:
            server()
                    .perform(
                        get( "/management/v1/reindex" )
                        .accept( "application/json" )
                    )
                    .andExpect(
                        status().isOk()
                    )
        when:
            def response = JSON( server()
                    .perform(
                        get( "/v3/search?q=CPERSON2" )
                        .with( apiToken() )
                        .accept( "application/json" )
                    )
                    .andExpect(
                        status().isOk()
                    ).andReturn()
            );
        then:
            response.contains( JSON(
                    '''
                    {
                        "index" : "CPERSON2 | EPERSON2",
                        "word"  : "CPERSON2",
                        "type"  : "PERSON"
                    }
                    '''
            ) )
    }
}
