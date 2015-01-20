package tw.edu.ncu.cc.location.server.converter;

import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;

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
