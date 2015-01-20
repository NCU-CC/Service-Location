package tw.edu.ncu.cc.location.server.service.impl;

import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.service.UnitService;
import tw.edu.ncu.cc.location.server.service.tool.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class UnitServiceImpl extends HibernateAccessTool implements UnitService {

    @Override
    public void persistUnits( UnitEntity... unitEntities ) {
        persistObjects( ( Object[] ) unitEntities );
    }

    @Override
    public UnitEntity getUnit( Integer id ) {
        return ( UnitEntity ) getObject( id, UnitEntity.class );
    }

    @Override
    public Set<UnitEntity> getUnits( String fullName ) {
        return new HashSet<>(
            getObjects(
                    UnitEntity.class,
                    getSession()
                            .createQuery( "from UnitEntity where fullName = :fname" )
                            .setString( "fname", fullName )
            )
        );
    }

}
