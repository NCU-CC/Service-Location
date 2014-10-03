package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.unit.UnitWrapper;
import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.tool.Type;
import tw.edu.ncu.cc.location.server.tool.convert.UnitEntity_UnitConverter;


public class ServerPersonWrapper extends UnitWrapper {

    public ServerPersonWrapper( PersonEntity personEntity ) {
        if( personEntity != null ) {
            Unit[] units = new Unit[2];
            UnitEntity_UnitConverter converter = new UnitEntity_UnitConverter();
            units[0] = Type.convert( personEntity.getPrimaryUnit(),   converter );
            units[1] = Type.convert( personEntity.getSecondaryUnit(), converter );
            setResult( units );
        }
    }

}
