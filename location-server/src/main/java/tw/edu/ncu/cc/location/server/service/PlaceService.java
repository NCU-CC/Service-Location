package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;

import java.util.List;

public interface PlaceService {
    public List< PlaceEntity > getPlaces( String chineseName );
    public List< PlaceEntity > getPlaces( PlaceType type );
    public List< PlaceEntity > getPlaces( int offset, int max );
    public List< UnitEntity >  getUnits( String chineseName );
}
