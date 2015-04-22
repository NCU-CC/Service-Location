package tw.edu.ncu.cc.location.server.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tw.edu.ncu.cc.location.data.place.Place
import tw.edu.ncu.cc.location.server.entity.PlaceEntity

@Component
public class PlaceEntity_PlaceConverter implements Converter< PlaceEntity, Place > {

    @Autowired
    def Point_LocationConverter converter

    @Override
    public Place convert( PlaceEntity placeEntity ) {
        new Place(
            chineseName: placeEntity.chineseName,
                englishName: placeEntity.englishName,
                pictureName: placeEntity.pictureName,
                type: placeEntity.type,
                location: placeEntity.location == null ? null : converter.convert( placeEntity.location )
        )
    }

}
