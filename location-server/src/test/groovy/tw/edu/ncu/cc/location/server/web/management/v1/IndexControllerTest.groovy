package tw.edu.ncu.cc.location.server.web.management.v1

import specification.IntegrationSpecification

import static helper.IpMockMvcRequestProcessor.remoteAddress
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class IndexControllerTest extends IntegrationSpecification {

    def "it should provide api token to use api"() {
        expect:
            server()
                    .perform(
                        get( "/management/v1/reindex" )
                        .accept( "application/json" )
                    )
                    .andExpect(
                        status().isOk()
                    )
    }

    def "it should restrict the user ip"() {
        expect:
            server()
                    .perform(
                        get( "/management/v1/reindex" )
                        .with( remoteAddress( "140.115.8.10" ) )
                        .accept( "application/json" )
                    )
                    .andExpect(
                        status().isForbidden()
                    )
    }

}
