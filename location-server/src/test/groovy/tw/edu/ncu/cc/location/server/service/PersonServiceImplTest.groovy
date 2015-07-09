package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification

class PersonServiceImplTest extends SpringSpecification {

    @Autowired
    private PersonServiceImpl personService;

    def "it can get people by chinese name"() {
        when:
            def people = personService.getPeopleByChineseName( "CPERSON1" )
        then:
            people.get( 0 ).title == "TITLE1"
    }

    def "it cam find person by portal id"() {
        when:
            def person = personService.findByPortalId( "PORTAL1" )
        then:
            person.portalId == "PORTAL1"
    }

}
