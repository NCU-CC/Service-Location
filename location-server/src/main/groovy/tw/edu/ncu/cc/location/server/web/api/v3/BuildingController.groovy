package tw.edu.ncu.cc.location.server.web.api.v3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.TypeDescriptor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tw.edu.ncu.cc.location.data.building.Building
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity
import tw.edu.ncu.cc.location.server.service.PlaceUnitService

@RestController
@RequestMapping( value = "v3/buildings", method = RequestMethod.GET )
public class BuildingController {

    @Autowired
    def PlaceUnitService placeUnitService

    @Autowired
    def ConversionService conversionService

    @RequestMapping
    public ResponseEntity index() {
        new ResponseEntity<>(
                conversionService.convert(
                        placeUnitService.getAllPlaceUnits(),
                        TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( PlaceUnitEntity.class ) ),
                        TypeDescriptor.array( TypeDescriptor.valueOf( Building.class ) )
                ),
                HttpStatus.OK
        )
    }

}
