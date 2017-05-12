package tw.edu.ncu.cc.location.server.web.api.v1

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.accessToken

class WordControllerTest extends IntegrationSpecification {

    def token = accessToken().user( "user-uid" ).scope( "user.info.basic.read" )

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
                        get( "/v1/search?q=CPERSON2" )
                        .with( token )
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
