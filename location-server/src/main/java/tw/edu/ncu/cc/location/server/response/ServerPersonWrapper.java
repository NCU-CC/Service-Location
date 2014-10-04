package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.person.PersonWrapper;
import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.tool.Type;
import tw.edu.ncu.cc.location.server.tool.convert.PersonEntity_PersonConverter;

import java.util.Set;


public class ServerPersonWrapper extends PersonWrapper {

    public ServerPersonWrapper( Set<PersonEntity> personEntitys ) { //TODO SIMPLIFY
        if( personEntitys != null && personEntitys.size() > 0 ) {
            Person[] result = new Person[ personEntitys.size() ];
            int i = 0;
            for( PersonEntity personEntity : personEntitys ) {
                result[ i++ ] = Type.convert( personEntity, new PersonEntity_PersonConverter() );
            }
            setResult( result );
        }
    }

}
