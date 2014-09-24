package tw.edu.ncu.cc.location.response.location;

import tw.edu.ncu.cc.location.db.data.Location;

import java.util.Set;

@SuppressWarnings( "unused" )
public class LocationNoNameWrapper {

    private LocationNoName[] result;

    public LocationNoNameWrapper(){}

    public LocationNoNameWrapper( Set<Location> locations ) {
        result = new LocationNoName[ locations.size() ];
        int i = 0;
        for( Location location : locations ) {
            result[ i++ ] = new LocationNoName( location );
        }
    }

    public LocationNoName[] getResult() {
        return result;
    }

    public void setResult( LocationNoName[] result ) {
        this.result = result;
    }

}
