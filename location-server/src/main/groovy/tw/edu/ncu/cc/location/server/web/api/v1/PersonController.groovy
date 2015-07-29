package tw.edu.ncu.cc.location.server.web.api.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.TypeDescriptor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tw.edu.ncu.cc.location.data.person.Person
import tw.edu.ncu.cc.location.server.entity.PersonEntity
import tw.edu.ncu.cc.location.server.helper.ResourceHelper
import tw.edu.ncu.cc.location.server.service.PersonService

import static tw.edu.ncu.cc.location.server.helper.ResourceHelper.nullNotFound

@RestController
@RequestMapping( value = "v1/faculties", method = RequestMethod.GET )
public class PersonController {

    @Autowired
    def PersonService personService

    @Autowired
    def ConversionService conversionService

    @RequestMapping
    public ResponseEntity index( @RequestParam( value = "cname", required = true ) String name ) {
        new ResponseEntity<>(
                conversionService.convert(
                        personService.getPeopleByChineseName( name ),
                        TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( PersonEntity.class ) ),
                        TypeDescriptor.array( TypeDescriptor.valueOf( Person.class ) )
                ),
                HttpStatus.OK
        )
    }

}
