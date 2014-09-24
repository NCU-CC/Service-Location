package tw.edu.ncu.cc.location.response.person;

import tw.edu.ncu.cc.location.db.data.Person;
import tw.edu.ncu.cc.location.db.data.Unit;
import tw.edu.ncu.cc.location.response.unit.UnitNoID;

import java.util.Set;

@SuppressWarnings( "unused" )
public class PersonLocationWrapper {

    private UnitNoID[] result;

    public PersonLocationWrapper( Person person ) {
        Set<Unit> units = person.getUnits();
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
