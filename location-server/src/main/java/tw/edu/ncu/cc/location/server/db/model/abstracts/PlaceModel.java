package tw.edu.ncu.cc.location.server.db.model.abstracts;

import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.db.data.Place;

import java.util.Set;

public interface PlaceModel {
    public void persistPlace( Place... places );
    public Place getPlace( Integer id );
    public Set<Place> getPlaces( String name );
    public Set<Place> getPlaces( PlaceType type );
}
