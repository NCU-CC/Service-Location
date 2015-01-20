package tw.edu.ncu.cc.location.server.converter;

import org.springframework.core.convert.converter.Converter;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.server.entity.PersonEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;

public class PersonEntity_PersonConverter implements Converter< PersonEntity, Person > {

    private Converter< UnitEntity, Unit > converter = new UnitEntity_UnitConverter();

    @Override
    public Person convert( PersonEntity personEntity ) {
        Person person = new Person();
        person.setChineseName( personEntity.getChineseName() );
        person.setEnglishName( personEntity.getEnglishName() );
        person.setTitle( personEntity.getTitle() );
        if( personEntity.getPrimaryUnit() != null ) {
            person.setPrimaryUnit  ( converter.convert( personEntity.getPrimaryUnit() ) );
        }
        if( personEntity.getSecondaryUnit() != null ) {
            person.setSecondaryUnit( converter.convert( personEntity.getSecondaryUnit() ) );
        }
        person.setOfficePhone( personEntity.getOfficePhone() );
        return person;
    }

}
