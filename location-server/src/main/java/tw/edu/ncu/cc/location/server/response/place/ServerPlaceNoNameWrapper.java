package tw.edu.ncu.cc.location.server.response.place;

import tw.edu.ncu.cc.location.data.place.PlaceNoName;
import tw.edu.ncu.cc.location.data.place.PlaceNoNameWrapper;
import tw.edu.ncu.cc.location.server.db.data.Place;

import java.util.Set;

@SuppressWarnings( "unused" )
public class ServerPlaceNoNameWrapper extends PlaceNoNameWrapper {

    public ServerPlaceNoNameWrapper( Set<Place> places ) {
        PlaceNoName[] result = new PlaceNoName[ places.size() ];
        int i = 0;
        for( Place place : places ) {
            result[ i++ ] = build( place );
        }
        setResult( result );
    }

    private PlaceNoName build( Place place ) {
        PlaceNoName placeNoName = new PlaceNoName();
        placeNoName.setType( place.getType().value() );
        placeNoName.setLng( place.getLongitude() );
        placeNoName.setLat( place.getLatitude() );
        placeNoName.setPicName( place.getPictureName() );
        return placeNoName;
    }

}
