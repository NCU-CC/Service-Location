package tw.edu.ncu.cc.location.response.location;

import tw.edu.ncu.cc.location.db.data.Location;

import java.util.Set;

@SuppressWarnings( "unused" )
public class LocationNoTypeWrapper {

    private LocationNoType[] result;

    public LocationNoTypeWrapper(){}

    public LocationNoTypeWrapper( Set<Location> locations ) {
        result = new LocationNoType[ locations.size() ];
        int i = 0;
        for( Location location : locations ) {
            result[ i++ ] = new LocationNoType( location );
        }
    }

    public LocationNoType[] getResult() {
        return result;
    }

    public void setResult( LocationNoType[] result ) {
        this.result = result;
    }

}
