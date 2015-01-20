package tw.edu.ncu.cc.location.server.controller.api.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.exception.handler.APIExceptionHandler;
import tw.edu.ncu.cc.location.server.service.UnitService;

import java.util.List;

@RestController
@RequestMapping( value = "unit", method = RequestMethod.GET )
public class UnitController extends APIExceptionHandler {

    private UnitService unitService;
    private ConversionService conversionService;

    @Autowired
    public void setUnitService( UnitService unitService ) {
        this.unitService = unitService;
    }

    @Autowired
    public void setConversionService( ConversionService conversionService ) {
        this.conversionService = conversionService;
    }

    @RequestMapping( value = "name/{name}" )
    public Unit[] getUnitByName( @PathVariable( "name" ) String name ) {
        return ( Unit[] ) conversionService.convert(
                unitService.getUnits( name ),
                TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( UnitEntity.class ) ),
                TypeDescriptor.array( TypeDescriptor.valueOf( Unit.class ) )
        );
    }

}
