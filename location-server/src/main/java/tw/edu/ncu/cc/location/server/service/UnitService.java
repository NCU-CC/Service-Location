package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.server.entity.UnitEntity;

import java.util.List;

public interface UnitService {
    public List< UnitEntity > getUnits( String fullName );
    public List< UnitEntity > getUnits( int offset, int max );
}
