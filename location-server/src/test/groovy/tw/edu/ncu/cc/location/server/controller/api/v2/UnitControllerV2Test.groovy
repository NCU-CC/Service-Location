package tw.edu.ncu.cc.location.server.controller.api.v2

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class UnitControllerV2Test extends IntegrationSpecification {

    def "it can get units by full name"() {
        when:
            def response = JSON( server()
                    .perform( get( "/api/v2/unit/name/FUNIT1" ).accept( "application/json" ) )
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

}
