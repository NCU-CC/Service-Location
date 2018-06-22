package tw.edu.ncu.cc.location.server.web.api.v1

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.apiToken


class UnitControllerTest extends IntegrationSpecification {

    def "it should provide api token to use api"() {
        expect:
            server()
                    .perform(
                        get( "/v1/units?fname=FUNIT1" )
                        .accept( "application/json" )
                    )
                    .andExpect(
                        status().isBadRequest()
                    )
    }

    def "it can get units by full name"() {
        when:
            def response = JSON( server()
                    .perform(
                        get( "/v1/units?fname=FUNIT1" )
                        .with( apiToken() )
                        .accept( "application/json" )
                    )
                    .andExpect( status().isOk() )
                    .andReturn()
            );
        then:
            response.contains( JSON(
                    '''
                    {
                        "unitCode"   : "A100",
                        "chineseName": "CUNIT1",
                        "englishName": "EUNIT1",
                        "shortName"  : "U1",
                        "fullName"   : "FUNIT1",
                        "url" : "http://www.example.com",
                        "location" : null
                    }
                    '''
            ) )
    }

    def "it can get units in place by chinese name of place"() {
        when:
            def response = JSON( server()
                    .perform(
                        get( "/v1/units?building_cname=CPLACE2" )
                        .with( apiToken() )
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
                        "unitCode"   : "A110",
                        "chineseName": "CUNIT2",
                        "englishName": "EUNIT2",
                        "shortName"  : "U2",
                        "fullName"   : "FUNIT2",
                        "url" : "http://www.example.com",
                        "location" : null
                    }
                    '''
            ) )
    }

    def "it should response error message when no parameters represented"() {
        expect:
            server()
                .perform(
                    get( "/v1/units" )
                    .with( apiToken() )
                    .accept( "application/json" )
                )
                .andExpect(
                    status().isBadRequest()
                )
    }

}
