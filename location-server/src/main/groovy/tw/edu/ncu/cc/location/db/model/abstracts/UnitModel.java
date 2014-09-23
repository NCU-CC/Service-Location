package tw.edu.ncu.cc.location.db.model.abstracts;

import tw.edu.ncu.cc.location.db.data.Unit;
import tw.edu.ncu.cc.location.db.data.UnitType;

import java.util.Set;

public interface UnitModel {
    public void persistUnits( Unit...units );
    public Unit getUnit( Integer id );
    public Unit getUnit( String name );
    public Set<Unit> getUnits( UnitType type );
}
