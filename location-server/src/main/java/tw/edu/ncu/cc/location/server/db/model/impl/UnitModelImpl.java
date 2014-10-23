package tw.edu.ncu.cc.location.server.db.model.impl;

import tw.edu.ncu.cc.location.server.db.data.UnitEntity;
import tw.edu.ncu.cc.location.server.db.model.UnitModel;
import tw.edu.ncu.cc.location.server.db.model.tool.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class UnitModelImpl extends HibernateAccessTool implements UnitModel {

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
