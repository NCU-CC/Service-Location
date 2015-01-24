package tw.edu.ncu.cc.location.server.service.impl

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification

class PersonServiceImplTest extends SpringSpecification {

    @Autowired
    private PersonServiceImpl personService;

    def "it can get people by chinese name"() {
        when:
            def people = personService.getPeople( "CPERSON1" )
        then:
            people.get( 0 ).title == "TITLE1"
    }

    def "it can get peolple to be indexed by pagination"() {
        given:
            def page = 0
            def per = 1
        when:
            def people = personService.getPeopleToBeIndexed( page, per );
        then:
            people.get( 0 ).title == "TITLE1"
    }

}
