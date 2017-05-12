package tw.edu.ncu.cc.location.server.web.api.v1

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static tw.edu.ncu.cc.oauth.resource.test.ApiAuthMockMvcRequestPostProcessors.accessToken


class PlaceControllerTest extends IntegrationSpecification {

    def token = accessToken().user( "user-uid" ).scope( "user.info.basic.read" )

    def "it should provide api token to use api"() {
        expect:
            server()
                    .perform(
                        get( "/v1/places?type=WHEELCHAIR_RAMP" )
                        .accept( "application/json" )
                    )
                    .andExpect(
                        status().isBadRequest()
                    )
    }

    def "it can get place info by place type"() {
        when:
            def response = JSON( server()
                    .perform(
                        get( "/v1/places?type=WHEELCHAIR_RAMP" )
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
                        "englishName" : "EPLACE1",
                        "pictureName" : null,
                        "type" : "WHEELCHAIR_RAMP",
                        "location" : null
                    }
                    '''
            ) )
    }

    def "it can get place info by place type 2"() {
        expect:
            server()
                    .perform(
                        get( "/v1/places?type=NOT_EXIST" )
                        .with( token )
                        .accept( "application/json" )
                    )
                    .andExpect( status().isBadRequest(  ) )
    }

    def "it can get place info by chinese name"() {
        when:
            def response = JSON( server()
                    .perform(
                        get( "/v1/places?cname=CPLACE3" )
                        .with( token )
                        .accept( "application/json" )
                    )
                    .andExpect( status().isOk() )
                    .andReturn()
            );
        then:
            response.contains( JSON(
                    '''
                    {
                        "chineseName" : "CPLACE3",
                        "englishName" : "EPLACE3",
                        "pictureName" : null,
                        "type" : "RESEARCH",
                        "location" : null
                    }
                    '''
            ) )
    }

    def "it should response error message when no parameters represented"() {
        expect:
            server()
                .perform(
                    get( "/v1/places" )
                    .with( token )
                    .accept( "application/json" )
                )
                .andExpect(
                    status().isBadRequest()
                )
    }

}
