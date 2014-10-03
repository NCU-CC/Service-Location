package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.unit.UnitWrapper;
import tw.edu.ncu.cc.location.server.db.data.UnitEntity;
import tw.edu.ncu.cc.location.server.tool.Type;
import tw.edu.ncu.cc.location.server.tool.convert.UnitEntity_UnitConverter;

import java.util.Set;

public class ServerUnitWrapper extends UnitWrapper {

    public ServerUnitWrapper( Set<UnitEntity> unitEntities ) {
        if( unitEntities != null && unitEntities.size() > 0 ) {
            Unit[] result = new Unit[ unitEntities.size() ];
            int i = 0;
            for( UnitEntity unitEntity : unitEntities ) {
                result[ i++ ] = Type.convert( unitEntity, new UnitEntity_UnitConverter() );
            }
            setResult( result );
        }
    }

}
