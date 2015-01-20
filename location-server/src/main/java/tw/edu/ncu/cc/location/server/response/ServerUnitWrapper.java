package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.converter.Type;
import tw.edu.ncu.cc.location.server.converter.UnitEntity_UnitConverter;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;

import java.util.Set;

public class ServerUnitWrapper extends ResultWrapper<Unit> {

    public ServerUnitWrapper( Set<UnitEntity> unitEntities ) {
        setResult(
                Type.convert(
                        unitEntities,
                        Unit.class,
                        new UnitEntity_UnitConverter()
                )
        );
    }

}
