package tw.edu.ncu.cc.location.server.db.model.abstracts;

import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;

import java.util.Set;

public interface PlaceModel {
    public void persistPlace( PlaceEntity... placeEntities );
    public PlaceEntity getPlace( Integer id );
    public Set<PlaceEntity> getPlaces( String chineseName );
    public Set<PlaceEntity> getPlaces( PlaceType type );
    public Set<PlaceEntity> getAllPlaces();
}
