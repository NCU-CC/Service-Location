package tw.edu.ncu.cc.location.server.converter;

import com.vividsolutions.jts.geom.Point;
import org.springframework.core.convert.converter.Converter;
import tw.edu.ncu.cc.location.data.location.Location;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;

public class PlaceEntity_PlaceConverter implements Converter< PlaceEntity, Place > {

    private Converter< Point, Location > converter = new Point_LocationConverter();

    @Override
    public Place convert( PlaceEntity placeEntity ) {
        Place place = new Place();
        place.setChineseName( placeEntity.getChineseName() );
        place.setEnglishName( placeEntity.getEnglishName() );
        place.setPictureName( placeEntity.getPictureName() );
        place.setType( placeEntity.getType() );
        if( placeEntity.getLocation() != null ) {
            place.setLocation( converter.convert( placeEntity.getLocation() ) );
        }
        return place;
    }

}
