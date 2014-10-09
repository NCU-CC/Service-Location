package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.tool.Type;
import tw.edu.ncu.cc.location.server.tool.convert.PersonEntity_PersonConverter;

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

