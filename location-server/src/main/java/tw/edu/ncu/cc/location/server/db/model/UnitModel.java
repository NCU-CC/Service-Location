package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.server.db.data.UnitEntity;

import java.util.Set;

public interface UnitModel {
    public void persistUnits( UnitEntity... unitEntities );
    public UnitEntity getUnit( Integer id );
    public Set<UnitEntity> getUnits( String fullName );
}
