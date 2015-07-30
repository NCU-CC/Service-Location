package tw.edu.ncu.cc.location.server.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tw.edu.ncu.cc.location.data.person.Person
import tw.edu.ncu.cc.location.server.entity.PersonEntity

@Component
public class PersonEntity_PersonConverter implements Converter< PersonEntity, Person > {

    @Autowired
    def UnitEntity_UnitConverter converter

    @Override
    public Person convert( PersonEntity personEntity ) {
        new Person(
                title: personEntity.title,
                chineseName: personEntity.chineseName,
                englishName: personEntity.englishName,
                officePhone: personEntity.officePhone,
                primaryUnit:   personEntity.primaryUnit   == null ? null : converter.convert( personEntity.primaryUnit ),
                secondaryUnit: personEntity.secondaryUnit == null ? null : converter.convert( personEntity.secondaryUnit )
        )
    }

}
