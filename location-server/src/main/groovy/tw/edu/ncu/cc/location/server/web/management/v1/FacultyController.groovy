package tw.edu.ncu.cc.location.server.web.management.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tw.edu.ncu.cc.location.data.person.Person
import tw.edu.ncu.cc.location.server.service.PersonService

import static tw.edu.ncu.cc.location.server.helper.ResourceHelper.nullNotFound

@RestController
@RequestMapping( value = "management/v1/faculties", method = RequestMethod.GET )
public class FacultyController {

    @Autowired
    def PersonService personService

    @Autowired
    def ConversionService conversionService

    @RequestMapping( value = "{portal_id}" )
    def show( @PathVariable( "portal_id" ) String portalId ) {
        new ResponseEntity<>(
                conversionService.convert(
                        nullNotFound( personService.findByPortalId( portalId ) ), Person.class
                ),
                HttpStatus.OK
        )
    }

}