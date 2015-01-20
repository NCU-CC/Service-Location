package tw.edu.ncu.cc.location.server.controller.api.v2

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class PersonControllerV2Test extends IntegrationSpecification {

    def "it can get person info by chinese name"() {
        when:
            def response = JSON( server()
                    .perform( get( "/api/v2/person/name/CPERSON1" ).accept( "application/json" ) )
                    .andExpect( status().isOk() )
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

}
