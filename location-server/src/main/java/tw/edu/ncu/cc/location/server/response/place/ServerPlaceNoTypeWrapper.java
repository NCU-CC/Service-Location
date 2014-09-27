package tw.edu.ncu.cc.location.server.response.place;

import tw.edu.ncu.cc.location.data.place.PlaceNoType;
import tw.edu.ncu.cc.location.data.place.PlaceNoTypeWrapper;
import tw.edu.ncu.cc.location.server.db.data.Place;

import java.util.Set;

@SuppressWarnings( "unused" )
public class ServerPlaceNoTypeWrapper extends PlaceNoTypeWrapper {

    public ServerPlaceNoTypeWrapper( Set<Place> places ) {
        if( places.size() > 0 ) {
            PlaceNoType[] result = new PlaceNoType[ places.size() ];
            int i = 0;
            for( Place place : places ) {
                result[ i++ ] = build( place );
            }
            setResult( result );
        }
    }

    private PlaceNoType build( Place place ) {
        PlaceNoType placeNoType = new PlaceNoType();
        placeNoType.setName( place.getName() );
        placeNoType.setLng( place.getLongitude() );
        placeNoType.setLat( place.getLatitude() );
        placeNoType.setPicName( place.getPictureName() );
        return placeNoType;
    }

}
