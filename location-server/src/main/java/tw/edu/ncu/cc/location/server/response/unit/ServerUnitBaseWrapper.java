package tw.edu.ncu.cc.location.server.response.unit;

import tw.edu.ncu.cc.location.data.unit.UnitBase;
import tw.edu.ncu.cc.location.data.unit.UnitBaseWrapper;
import tw.edu.ncu.cc.location.server.db.data.Unit;

import java.util.Set;

@SuppressWarnings( "unused" )
public class ServerUnitBaseWrapper extends UnitBaseWrapper {

    public ServerUnitBaseWrapper( Set<Unit> units ) {
        if( units.size() > 0 ) {
            UnitBase[] result = new UnitBase[ units.size() ];
            int i = 0;
            for( Unit unit : units ) {
                result[ i++ ] = build( unit );
            }
            setResult( result );
        }
    }

    private UnitBase build( Unit unit ) {
        UnitBase unitBase = new UnitBase();
        unitBase.setLat( unit.getLatitude() );
        unitBase.setLng( unit.getLongitude() );
        unitBase.setUrl( unit.getUrl() );
        return unitBase;
    }

}
