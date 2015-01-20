package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.converter.PlaceEntity_PlaceConverter;
import tw.edu.ncu.cc.location.server.converter.Type;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;

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
