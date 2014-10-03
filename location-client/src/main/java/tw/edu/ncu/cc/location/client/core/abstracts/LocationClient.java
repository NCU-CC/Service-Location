package tw.edu.ncu.cc.location.client.core.abstracts;

import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.Unit;

import java.util.Set;

public interface LocationClient {

    public Set<Place> getPlaces( String placeName );
    public Set<Place> getPlaces( PlaceType placeType );

    public Set<Unit> getPersonUnits( String personName );
    public Set<Unit> getUnits( String unitName );

}


