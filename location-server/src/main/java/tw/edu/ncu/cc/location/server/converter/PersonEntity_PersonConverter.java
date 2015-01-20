package tw.edu.ncu.cc.location.server.converter;

import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.server.entity.PersonEntity;

public class PersonEntity_PersonConverter implements Converter<PersonEntity,Person> {

    @Override
    public Person convertFrom( PersonEntity personEntity ) {
        UnitEntity_UnitConverter converter = new UnitEntity_UnitConverter();
        Person person = new Person();
        person.setChineseName( personEntity.getChineseName() );
        person.setEnglishName( personEntity.getEnglishName() );
        person.setTitle( personEntity.getTitle() );
        person.setPrimaryUnit  ( Type.convert( personEntity.getPrimaryUnit(),   converter ) );
        person.setSecondaryUnit( Type.convert( personEntity.getSecondaryUnit(), converter ) );
        person.setOfficePhone( personEntity.getOfficePhone() );
        return person;
    }

}
