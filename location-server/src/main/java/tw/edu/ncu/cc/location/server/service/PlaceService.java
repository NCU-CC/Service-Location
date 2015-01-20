package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;

import java.util.Set;

public interface PlaceService {
    public void persistPlace( PlaceEntity... placeEntities );
    public PlaceEntity getPlace( Integer id );
    public Set<PlaceEntity> getPlaces( String chineseName );
    public Set<PlaceEntity> getPlaces( PlaceType type );
    public Set<UnitEntity> getUnits( String chineseName );
}
