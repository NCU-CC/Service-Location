package tw.edu.ncu.cc.location.server.response;

import com.vividsolutions.jts.geom.Point;
import tw.edu.ncu.cc.location.data.location.Position;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.unit.UnitWrapper;
import tw.edu.ncu.cc.location.server.db.data.UnitEntity;

import java.util.Set;

public class ServerUnitWrapper extends UnitWrapper {

    public ServerUnitWrapper( Set<UnitEntity> unitEntities ) {
        if( unitEntities != null && unitEntities.size() > 0 ) {
            Unit[] result = new Unit[ unitEntities.size() ];
            int i = 0;
            for( UnitEntity unitEntity : unitEntities ) {
                result[ i++ ] = build( unitEntity );
            }
            setResult( result );
        }
    }

    private Unit build( UnitEntity unitEntity ) {
        Unit unit = new Unit();
        unit.setUnitCode( unitEntity.getUnitCode() );
        unit.setChineseName( unitEntity.getChineseName() );
        unit.setEnglishName( unitEntity.getEnglishName() );
        unit.setShortName( unitEntity.getShortName() );
        unit.setFullName( unitEntity.getFullName() );
        unit.setUrl( unitEntity.getUrl() );
        unit.setLocation( fromPoint( unitEntity.getLocation() ) );
        return unit;
    }

    private Position fromPoint( Point point ) {
        if( point == null ) {
            return null;
        }
        Position position = new Position();
        position.setLat( point.getY() );
        position.setLng( point.getX() );
        return position;
    }

}
