package tw.edu.ncu.cc.location.server.response.place;

import tw.edu.ncu.cc.location.server.db.data.Place;

import java.util.Set;

@SuppressWarnings( "unused" )
public class PlaceNoNameWrapper {

    private PlaceNoName[] result;

    public PlaceNoNameWrapper(){}

    public PlaceNoNameWrapper( Set<Place> places ) {
        result = new PlaceNoName[ places.size() ];
        int i = 0;
        for( Place place : places ) {
            result[ i++ ] = new PlaceNoName( place );
        }
    }

    public PlaceNoName[] getResult() {
        return result;
    }

    public void setResult( PlaceNoName[] result ) {
        this.result = result;
    }

}
