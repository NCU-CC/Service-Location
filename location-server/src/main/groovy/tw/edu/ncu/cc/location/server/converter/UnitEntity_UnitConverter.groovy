package tw.edu.ncu.cc.location.server.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tw.edu.ncu.cc.location.data.unit.Unit
import tw.edu.ncu.cc.location.server.entity.UnitEntity

@Component
public class UnitEntity_UnitConverter implements Converter< UnitEntity, Unit > {

    @Autowired
    def Point_LocationConverter converter

    @Override
    public Unit convert( UnitEntity unitEntity ) {
        new Unit(
                unitCode: unitEntity.unitCode,
                chineseName: unitEntity.chineseName,
                englishName: unitEntity.englishName,
                shortName: unitEntity.shortName,
                fullName: unitEntity.fullName,
                url: unitEntity.url,
                location: unitEntity.location == null ? null : converter.convert( unitEntity.location )
        )
    }

}
