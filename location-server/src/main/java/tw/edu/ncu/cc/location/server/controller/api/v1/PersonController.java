package tw.edu.ncu.cc.location.server.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.entity.PersonEntity;
import tw.edu.ncu.cc.location.server.service.PersonService;

import java.util.List;

@RestController
@RequestMapping( value = "person", method = RequestMethod.GET )
public class PersonController {

    private PersonService personService;
    private ConversionService conversionService;

    @Autowired
    public void setPersonService( PersonService personService ) {
        this.personService = personService;
    }

    @Autowired
    public void setConversionService( ConversionService conversionService ) {
        this.conversionService = conversionService;
    }

    @RequestMapping( value = "name/{name}" )
    public ResultWrapper getPersonLocationByName( @PathVariable( "name" ) String name ) {
        return new ResultWrapper<>(
                ( Person[] ) conversionService.convert(
                    personService.getPeople( name ),
                    TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( PersonEntity.class ) ),
                    TypeDescriptor.array( TypeDescriptor.valueOf( Person.class ) )
                )
        );
    }

}
