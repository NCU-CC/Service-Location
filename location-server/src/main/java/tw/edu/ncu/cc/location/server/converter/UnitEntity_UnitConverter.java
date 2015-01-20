package tw.edu.ncu.cc.location.server.converter;

import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;

public class UnitEntity_UnitConverter implements Converter< UnitEntity, Unit > {
    @Override
    public Unit convertFrom( UnitEntity unitEntity ) {
        Unit unit = new Unit();
        unit.setUnitCode( unitEntity.getUnitCode() );
        unit.setChineseName( unitEntity.getChineseName() );
        unit.setEnglishName( unitEntity.getEnglishName() );
        unit.setShortName( unitEntity.getShortName() );
        unit.setFullName( unitEntity.getFullName() );
        unit.setUrl( unitEntity.getUrl() );
        unit.setLocation(
            Type.convert( unitEntity.getLocation(), new Point_PositionConverter() )
        );
        return unit;
    }
}
