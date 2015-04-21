package tw.edu.ncu.cc.location.server.web.api.v3

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class PlaceControllerTest extends IntegrationSpecification {

    def "it can get place info by place type"() {
        when:
            def response = JSON( server()
                    .perform( get( "/v3/places?type=WHEELCHAIR_RAMP" ).accept( "application/json" ) )
                    .andExpect( status().isOk() )
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
                    .perform( get( "/v3/places?type=NOT_EXIST" ).accept( "application/json" ) )
                    .andExpect( status().isBadRequest(  ) )
    }

    def "it can get place info by chinese name"() {
        when:
            def response = JSON( server()
                    .perform( get( "/v3/places?name=CPLACE3" ).accept( "application/json" ) )
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
                    get( "/v3/places" )
                    .accept( "application/json" )
                )
                .andExpect(
                    status().isBadRequest()
                )
    }

}
