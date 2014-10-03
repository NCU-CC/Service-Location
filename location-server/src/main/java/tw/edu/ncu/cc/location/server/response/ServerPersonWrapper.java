package tw.edu.ncu.cc.location.server.response;

import com.vividsolutions.jts.geom.Point;
import tw.edu.ncu.cc.location.data.location.Position;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.unit.UnitWrapper;
import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.db.data.UnitEntity;


public class ServerPersonWrapper extends UnitWrapper {

    public ServerPersonWrapper( PersonEntity personEntity ) {
        if( personEntity != null ) {
            Unit[] units = new Unit[2];
            units[0] = build( personEntity.getPrimaryUnit() );
            units[1] = build( personEntity.getSecondaryUnit() );
            setResult( units );
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
