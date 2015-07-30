package tw.edu.ncu.cc.location.server.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tw.edu.ncu.cc.location.data.building.Building
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity

@Component
class PlaceUnitEntity_BuildingConverter implements Converter< PlaceUnitEntity, Building > {

    @Override
    Building convert( PlaceUnitEntity source ) {
        new Building(
            chineseName: source.place.chineseName,
            englishName: source.place.englishName
        )
    }

}
