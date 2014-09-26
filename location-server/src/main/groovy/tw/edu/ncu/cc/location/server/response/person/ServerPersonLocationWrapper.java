package tw.edu.ncu.cc.location.server.response.person;

import tw.edu.ncu.cc.location.data.person.PersonLocationWrapper;
import tw.edu.ncu.cc.location.data.unit.UnitNoID;
import tw.edu.ncu.cc.location.server.db.data.Person;
import tw.edu.ncu.cc.location.server.db.data.Unit;

import java.util.Set;

@SuppressWarnings( "unused" )
public class ServerPersonLocationWrapper extends PersonLocationWrapper {

    public ServerPersonLocationWrapper( Person person ) {
        Set<Unit> units = person.getUnits();
        UnitNoID[] result = new UnitNoID[ units.size() ];
        int i = 0;
        for( Unit unit : units ) {
            result[ i++ ] = build( unit );
        }
        setResult( result );
    }

    private UnitNoID build( Unit unit ) {
        UnitNoID unitNoID = new UnitNoID();
        unitNoID.setName( unit.getName() );
        unitNoID.setLng( unit.getLongitude() );
        unitNoID.setLat( unit.getLatitude() );
        unitNoID.setUrl( unit.getUrl() );
        return unitNoID;
    }

}
