package tw.edu.ncu.cc.location.server.web.api.v1

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.apiToken

class PersonControllerTest extends IntegrationSpecification {

    def "it should provide api token to use api"() {
        expect:
            server()
                    .perform(
                            get( "/v1/faculties?cname=CPERSON1" )
                            .accept( "application/json" )
                    )
                    .andExpect(
                            status().isBadRequest()
                    )
    }

    def "it should provide faculty info by chinese name 1"() {
        when:
            def response = JSON(
                    server()
                        .perform(
                            get( "/v1/faculties?cname=CPERSON1" )
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
                        "chineseName" : "CPERSON1",
                        "englishName" : null,
                        "title" : "TITLE1",
                        "primaryUnit" : {
                            "unitCode"   : "A100",
                            "chineseName": "CUNIT1",
                            "englishName": "EUNIT1",
                            "shortName"  : "U1",
                            "fullName"   : "FUNIT1",
                            "url"        : "http://www.example.com",
                            "location"   : null
                        },
                        "secondaryUnit" : {
                            "unitCode"   : "A110",
                            "chineseName": "CUNIT2",
                            "englishName": "EUNIT2",
                            "shortName"  : "U2",
                            "fullName"   : "FUNIT2",
                            "url"        : "http://www.example.com",
                            "location"   : null
                        },
                        "officePhone" : null
                    }
                    '''
            ) )
    }

    def "it should response error message when no parameters represented"() {
        expect:
            server()
                .perform(
                    get( "/v1/faculties" )
                    .with( apiToken() )
                    .accept( "application/json" )
                )
                .andExpect(
                    status().isBadRequest()
                )
    }

}
