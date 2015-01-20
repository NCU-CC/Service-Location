package tw.edu.ncu.cc.location.server.controller.api.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.exception.handler.APIExceptionHandler;
import tw.edu.ncu.cc.location.server.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping( value = "api/v2/place", method = RequestMethod.GET )
public class PlaceControllerV2 extends APIExceptionHandler {

    private PlaceService placeService;
    private ConversionService conversionService;

    @Autowired
    public void setPlaceService( PlaceService placeService ) {
        this.placeService = placeService;
    }

    @Autowired
    public void setConversionService( ConversionService conversionService ) {
        this.conversionService = conversionService;
    }

    @RequestMapping( value = "type/{type}" )
    public Place[] getPlaceByType( @PathVariable( "type" ) PlaceType type ) {
        return ( Place[] ) conversionService.convert(
                placeService.getPlaces( type ),
                TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( PlaceEntity.class ) ),
                TypeDescriptor.array( TypeDescriptor.valueOf( Place.class ) )
        );
    }

    @RequestMapping( value = "name/{name}" )
    public Place[] getPlaceByName( @PathVariable( "name" ) String name ) {
        return ( Place[] ) conversionService.convert(
                placeService.getPlaces( name ),
                TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( PlaceEntity.class ) ),
                TypeDescriptor.array( TypeDescriptor.valueOf( Place.class ) )
        );
    }

    @RequestMapping( value = "name/{name}/units" )
    public Unit[] getPlaceUnits( @PathVariable( "name" ) String name ) {
        return ( Unit[] ) conversionService.convert(
                placeService.getUnits( name ),
                TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( UnitEntity.class ) ),
                TypeDescriptor.array( TypeDescriptor.valueOf( Unit.class ) )
        );
    }

}
