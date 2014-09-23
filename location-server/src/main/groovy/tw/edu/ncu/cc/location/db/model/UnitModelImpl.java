package tw.edu.ncu.cc.location.db.model;

import tw.edu.ncu.cc.location.db.data.Unit;
import tw.edu.ncu.cc.location.db.data.UnitType;
import tw.edu.ncu.cc.location.db.model.abstracts.UnitModel;
import tw.edu.ncu.cc.location.db.model.base.HibernateAccessTool;

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
    public Unit getUnit( String name ) {
        return getObject( Unit.class, String.format( "name = '%s'", name ) );
    }

    @Override
    public Set<Unit> getUnits( UnitType type ) {
        return new HashSet<>(
                getObjects( Unit.class, String.format( "type = '%s'", type.toString() )  )
        );
    }

}
