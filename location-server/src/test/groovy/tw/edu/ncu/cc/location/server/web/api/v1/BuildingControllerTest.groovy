package tw.edu.ncu.cc.location.server.web.api.v1

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.apiToken
import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.accessToken


class BuildingControllerTest extends IntegrationSpecification {

    def token = accessToken().user( "user-uid" ).scope( "user.info.basic.read" )

    def "it should provide all buildings"() {
        when:
            def response = JSON(
                    server()
                            .perform(
                                get( "/v1/buildings" )
                                .with( token )
                                .accept( "application/json" )
                            )
                            .andExpect(
                                status().isOk()
                            )
                            .andReturn()
            );
        then:
            response.contains( JSON(
                    '''
                    {
                        "chineseName" : "CPLACE1",
                        "englishName" : "EPLACE1"
                    }
                    '''
            ) )
        and:
            response.contains( JSON(
                    '''
                    {
                        "chineseName" : "CPLACE2",
                        "englishName" : "EPLACE2"
                    }
                    '''
            ) )
    }

}
