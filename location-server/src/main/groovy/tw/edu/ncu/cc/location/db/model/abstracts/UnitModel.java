package tw.edu.ncu.cc.location.db.model.abstracts;

import tw.edu.ncu.cc.location.db.data.Unit;

import java.util.Set;

public interface UnitModel {
    public void persistUnits( Unit...units );
    public Unit getUnit( Integer id );
    public Set<Unit> getUnits( String name );
}
