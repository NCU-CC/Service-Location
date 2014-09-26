package tw.edu.ncu.cc.location.server.response.place;

import tw.edu.ncu.cc.location.server.db.data.Place;

import java.util.Set;

@SuppressWarnings( "unused" )
public class PlaceNoTypeWrapper {

    private PlaceNoType[] result;

    public PlaceNoTypeWrapper(){}

    public PlaceNoTypeWrapper( Set<Place> places ) {
        result = new PlaceNoType[ places.size() ];
        int i = 0;
        for( Place place : places ) {
            result[ i++ ] = new PlaceNoType( place );
        }
    }

    public PlaceNoType[] getResult() {
        return result;
    }

    public void setResult( PlaceNoType[] result ) {
        this.result = result;
    }

}
