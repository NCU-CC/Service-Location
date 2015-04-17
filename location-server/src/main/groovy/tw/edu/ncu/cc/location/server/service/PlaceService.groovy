package tw.edu.ncu.cc.location.server.service

import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.entity.PlaceEntity

public interface PlaceService {
    public List< PlaceEntity > getPlaces( String chineseName );
    public List< PlaceEntity > getPlaces( PlaceType type );
}
