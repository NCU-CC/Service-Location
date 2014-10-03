package tw.edu.ncu.cc.location.server.tool.convert;

import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.tool.Type;

public class PlaceEntity_PlaceConverter implements Converter<PlaceEntity,Place> {
    @Override
    public Place convertFrom( PlaceEntity placeEntity ) {
        Place place = new Place();
        place.setChineseName( placeEntity.getChineseName() );
        place.setEnglishName( placeEntity.getEnglishName() );
        place.setPictureName( placeEntity.getPictureName() );
        place.setType( placeEntity.getType() );
        place.setLocation(
                Type.convert( placeEntity.getLocation(), new Point_PositionConverter() )
        );
        return place;
    }
}
