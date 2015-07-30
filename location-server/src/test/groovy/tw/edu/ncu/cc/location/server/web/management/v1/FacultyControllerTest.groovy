package tw.edu.ncu.cc.location.server.web.management.v1

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.apiToken

class FacultyControllerTest extends IntegrationSpecification {


    def "it should provide faculty info by portal id"() {
        when:
            def response = JSON(
                    server().perform(
                            get( "/management/v1/faculties/PORTAL1" )
                                    .with( apiToken().clientTrusted() )
                                    .accept( "application/json" )
                    ).andExpect(
                            status().isOk()
                    ).andReturn()
            )
        then:
            response != null
        expect:
            server().perform(
                    get( "/management/v1/faculties/USERIDNOTEXIST" )
                            .with( apiToken().clientTrusted() )
                            .accept( "application/json" )
            ).andExpect(
                    status().isNotFound()
            )
    }

    def "it should not response for api token of none trusted client"() {
        expect:
            server().perform(
                    get( "/management/v1/faculties/PORTAL1" )
                            .accept( "application/json" )
            ).andExpect(
                    status().isBadRequest()
            )
        and:
            server().perform(
                    get( "/management/v1/faculties/PORTAL1" )
                            .with( apiToken() )
                            .accept( "application/json" )
            ).andExpect(
                    status().isForbidden()
            )
    }

}
