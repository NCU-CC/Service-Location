package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.tool.Type;
import tw.edu.ncu.cc.location.server.tool.convert.PlaceEntity_PlaceConverter;

import java.util.Set;

public class ServerPlaceWrapper extends ResultWrapper<Place> {

    public ServerPlaceWrapper( Set<PlaceEntity> placeEntities ) {
        setResult(
                Type.convert(
                        placeEntities,
                        Place.class,
                        new PlaceEntity_PlaceConverter()
                )
        );
    }

}
