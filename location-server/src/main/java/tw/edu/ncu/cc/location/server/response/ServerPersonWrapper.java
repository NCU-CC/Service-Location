package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.converter.PersonEntity_PersonConverter;
import tw.edu.ncu.cc.location.server.converter.Type;
import tw.edu.ncu.cc.location.server.entity.PersonEntity;

import java.util.Set;


public class ServerPersonWrapper extends ResultWrapper<Person> {

    public ServerPersonWrapper( Set<PersonEntity> personEntitys ) {
        setResult(
               Type.convert(
                   personEntitys,
                   Person.class,
                   new PersonEntity_PersonConverter()
               )
        );
    }

}

