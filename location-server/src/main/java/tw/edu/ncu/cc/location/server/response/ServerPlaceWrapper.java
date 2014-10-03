package tw.edu.ncu.cc.location.server.response;

import com.vividsolutions.jts.geom.Point;
import tw.edu.ncu.cc.location.data.location.Position;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceWrapper;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;

import java.util.Set;

public class ServerPlaceWrapper extends PlaceWrapper {

    public ServerPlaceWrapper( Set<PlaceEntity> placeEntities ) {
        if( placeEntities != null && placeEntities.size() > 0 ) {
            Place[] result = new Place[ placeEntities.size() ];
            int i = 0;
            for( PlaceEntity placeEntity : placeEntities ) {
                result[ i++ ] = build( placeEntity );
            }
            setResult( result );
        }
    }

    private Place build( PlaceEntity placeEntity ) {
        Place place = new Place();
        place.setChineseName( placeEntity.getChineseName() );
        place.setEnglishName( placeEntity.getEnglishName() );
        place.setPictureName( placeEntity.getPictureName() );
        place.setType( placeEntity.getType() );
        place.setLocation( fromPoint( placeEntity.getLocation()) );
        return place;
    }

    private Position fromPoint( Point point ) {
        if( point == null ) {
            return null;
        }
        Position position = new Position();
        position.setLat( point.getY() );
        position.setLng( point.getX() );
        return position;
    }

}
