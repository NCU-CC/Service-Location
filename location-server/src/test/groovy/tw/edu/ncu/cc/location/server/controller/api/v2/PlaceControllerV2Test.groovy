package tw.edu.ncu.cc.location.server.controller.api.v2

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class PlaceControllerV2Test extends IntegrationSpecification {

    def "it can get place info by place type"() {
        when:
            def response = JSON( server()
                    .perform( get( "/api/v2/place/type/WHEELCHAIR_RAMP" ).accept( "application/json" ) )
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
                    .perform( get( "/api/v2/place/type/NOT_EXIST" ).accept( "application/json" ) )
                    .andExpect( status().isBadRequest(  ) )
    }

    def "it can get place info by chinese name"() {
        when:
            def response = JSON( server()
                    .perform( get( "/api/v2/place/name/CPLACE2" ).accept( "application/json" ) )
                    .andExpect( status().isOk() )
                    .andReturn()
            );
        then:
            response.contains( JSON(
                    '''
                    {
                        "chineseName" : "CPLACE2",
                        "englishName" : "EPLACE2",
                        "pictureName" : null,
                        "type" : "WHEELCHAIR_RAMP",
                        "location" : null
                    }
                    '''
            ) )
    }

    def "it can get units in place by chinese name of place"() {
        when:
            def response = JSON( server()
                    .perform( get( "/api/v2/place/name/CPLACE2/units" ).accept( "application/json" ) )
                    .andExpect( status().isOk() )
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

}
