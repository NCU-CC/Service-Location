package tw.edu.ncu.cc.location.server.service.impl;

import tw.edu.ncu.cc.location.server.entity.PersonEntity;
import tw.edu.ncu.cc.location.server.service.PersonService;
import tw.edu.ncu.cc.location.server.service.tool.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class PersonServiceImpl extends HibernateAccessTool implements PersonService {

    @Override
    public void persistPeople( PersonEntity... personEntities ) {
        persistObjects( ( Object[] ) personEntities );
    }

    @Override
    public PersonEntity getPerson( Integer id ) {
        return ( PersonEntity ) getObject( id, PersonEntity.class );
    }

    @Override
    public PersonEntity getPerson( String chineseName ) {
        return getObject(
                PersonEntity.class,
                getSession()
                        .createQuery( "from PersonEntity where chineseName = :cname" )
                        .setString( "cname", chineseName )
        );
    }

    @Override
    public Set<PersonEntity> getPeople( String chineseName ) {
        return new HashSet<>(
                getObjects(
                    PersonEntity.class,
                    getSession()
                            .createQuery( "from PersonEntity where chineseName = :cname" )
                            .setString( "cname", chineseName )
                )
        );
    }

}
