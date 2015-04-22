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
import tw.edu.ncu.cc.location.data.place.Place
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.entity.PlaceEntity
import tw.edu.ncu.cc.location.server.service.PlaceService

@RestController
@RequestMapping( value = "v3/places", method = RequestMethod.GET )
public class PlaceController {

    @Autowired
    def PlaceService placeService

    @Autowired
    def ConversionService conversionService

    @RequestMapping
    public ResponseEntity index( @RequestParam( value = "type", required = false ) PlaceType type,
                                 @RequestParam( value = "cname", required = false ) String name ) {
        if( name == null && type == null ) {
            throw new MissingServletRequestParameterException( "type or cname", "" )
        }
        if( name != null ) {
            new ResponseEntity<>(
                    conversionService.convert(
                            placeService.getPlacesByChineseName( name ),
                            TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( PlaceEntity.class ) ),
                            TypeDescriptor.array( TypeDescriptor.valueOf( Place.class ) )
                    ),
                    HttpStatus.OK
            )
        } else {
            new ResponseEntity<>(
                    conversionService.convert(
                            placeService.getPlacesByPlaceType( type ),
                            TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( PlaceEntity.class ) ),
                            TypeDescriptor.array( TypeDescriptor.valueOf( Place.class ) )
                    ),
                    HttpStatus.OK
            )
        }
    }

}
