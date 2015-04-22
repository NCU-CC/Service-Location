package tw.edu.ncu.cc.location.server.web.api.v3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.TypeDescriptor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tw.edu.ncu.cc.location.data.unit.Unit
import tw.edu.ncu.cc.location.server.entity.UnitEntity
import tw.edu.ncu.cc.location.server.service.UnitService

@RestController
@RequestMapping( value = "v3/units", method = RequestMethod.GET )
public class UnitController {

    @Autowired
    def UnitService unitService

    @Autowired
    def ConversionService conversionService

    @RequestMapping
    public ResponseEntity index( @RequestParam( value = "fname", required = false ) String name,
                                 @RequestParam( value = "placeName", required = false ) String placeName ) {
        if( name == null && placeName == null ) {
            throw new MissingServletRequestParameterException( "name or placeName", "" )
        }

        if( name != null ) {
            new ResponseEntity<>(
                    conversionService.convert(
                            unitService.getUnitsByFullName( name ),
                            TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( UnitEntity.class ) ),
                            TypeDescriptor.array( TypeDescriptor.valueOf( Unit.class ) )
                    ),
                    HttpStatus.OK
            )
        } else {
            new ResponseEntity<>(
                    conversionService.convert(
                            unitService.getUnitsByPlaceChineseName( placeName ),
                            TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( UnitEntity.class ) ),
                            TypeDescriptor.array( TypeDescriptor.valueOf( Unit.class ) )
                    ),
                    HttpStatus.OK
            )
        }
    }

}
