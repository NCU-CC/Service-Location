package tw.edu.ncu.cc.location.server.web.management.v1

import specification.IntegrationSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FacultyControllerTest extends IntegrationSpecification {


    def "it should provide faculty info by portal id"() {
        when:
            def response = JSON(
                    server().perform(
                            get( "/management/v1/faculties/PORTAL1" )
                                    .accept( "application/json" )
                    ).andExpect(
                            status().isOk()
                    ).andReturn()
            )
        then:
            response != null
    }

}
