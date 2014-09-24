package tw.edu.ncu.cc.location.response.unit;

import tw.edu.ncu.cc.location.db.data.Unit;

import java.util.Set;

@SuppressWarnings( "unused" )
public class UnitNoIDWrapper {

    private UnitNoID[] result;

    public UnitNoIDWrapper( Set<Unit> units ) {
        result = new UnitNoID[ units.size() ];
        int i = 0;
        for( Unit unit : units ) {
            result[ i++ ] = new UnitNoID( unit );
        }
    }

    public UnitNoID[] getResult() {
        return result;
    }

    public void setResult( UnitNoID[] result ) {
        this.result = result;
    }

}
