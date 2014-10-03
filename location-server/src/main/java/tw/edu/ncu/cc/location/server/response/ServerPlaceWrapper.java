package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceWrapper;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.tool.Type;
import tw.edu.ncu.cc.location.server.tool.convert.PlaceEntity_PlaceConverter;

import java.util.Set;

public class ServerPlaceWrapper extends PlaceWrapper {

    public ServerPlaceWrapper( Set<PlaceEntity> placeEntities ) {
        if( placeEntities != null && placeEntities.size() > 0 ) {
            Place[] result = new Place[ placeEntities.size() ];
            int i = 0;
            for( PlaceEntity placeEntity : placeEntities ) {
                result[ i++ ] = Type.convert( placeEntity, new PlaceEntity_PlaceConverter() );
            }
            setResult( result );
        }
    }

}
