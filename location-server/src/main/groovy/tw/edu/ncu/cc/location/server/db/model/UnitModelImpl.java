package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.server.db.data.Unit;
import tw.edu.ncu.cc.location.server.db.model.abstracts.UnitModel;
import tw.edu.ncu.cc.location.server.db.model.base.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class UnitModelImpl extends HibernateAccessTool implements UnitModel {

    @Override
    public void persistUnits( Unit... units ) {
        persistObjects( ( Object[] ) units );
    }

    @Override
    public Unit getUnit( Integer id ) {
        return ( Unit ) getObject( id, Unit.class );
    }

    @Override
    public Set<Unit> getUnits( String name ) {
        return new HashSet<>(
            getObjects( Unit.class, String.format( "name = '%s'", name ) )
        );
    }

}
