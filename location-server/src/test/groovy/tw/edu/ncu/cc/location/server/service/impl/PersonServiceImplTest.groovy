package tw.edu.ncu.cc.location.server.service.impl

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification
import tw.edu.ncu.cc.location.server.service.PersonServiceImpl

class PersonServiceImplTest extends SpringSpecification {

    @Autowired
    private PersonServiceImpl personService;

    def "it can get people by chinese name"() {
        when:
            def people = personService.getPeopleByChineseName( "CPERSON1" )
        then:
            people.get( 0 ).title == "TITLE1"
    }

}
