package tw.edu.ncu.cc.location.server.converter;

import com.vividsolutions.jts.geom.Point;
import org.springframework.core.convert.converter.Converter;
import tw.edu.ncu.cc.location.data.location.Location;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;

public class UnitEntity_UnitConverter implements Converter< UnitEntity, Unit > {

    private Converter< Point, Location > converter = new Point_LocationConverter();

    @Override
    public Unit convert( UnitEntity unitEntity ) {
        Unit unit = new Unit();
        unit.setUnitCode( unitEntity.getUnitCode() );
        unit.setChineseName( unitEntity.getChineseName() );
        unit.setEnglishName( unitEntity.getEnglishName() );
        unit.setShortName( unitEntity.getShortName() );
        unit.setFullName( unitEntity.getFullName() );
        unit.setUrl( unitEntity.getUrl() );

        if( unitEntity.getLocation() != null ) {
            unit.setLocation( converter.convert( unitEntity.getLocation() ) );
        }
        return unit;
    }

}
