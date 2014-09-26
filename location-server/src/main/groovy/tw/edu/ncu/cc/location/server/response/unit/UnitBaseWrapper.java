package tw.edu.ncu.cc.location.server.response.unit;

import tw.edu.ncu.cc.location.server.db.data.Unit;

import java.util.Set;

@SuppressWarnings( "unused" )
public class UnitBaseWrapper {

    private UnitBase[] result;

    public UnitBaseWrapper( Set<Unit> units ) {
        result = new UnitBase[ units.size() ];
        int i = 0;
        for( Unit unit : units ) {
            result[ i++ ] = new UnitBase( unit );
        }
    }

    public UnitBase[] getResult() {
        return result;
    }

    public void setResult( UnitBase[] result ) {
        this.result = result;
    }

}
