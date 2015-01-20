package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.server.entity.UnitEntity;

import java.util.Set;

public interface UnitService {
    public void persistUnits( UnitEntity... unitEntities );
    public UnitEntity getUnit( Integer id );
    public Set<UnitEntity> getUnits( String fullName );
}
