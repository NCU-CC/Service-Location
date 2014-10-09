package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.server.db.model.base.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class PersonModelImpl extends HibernateAccessTool implements PersonModel {

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

    @Override
    public Set<PersonEntity> getAllPeople() {
        return new HashSet<>(
                getObjects(
                        PersonEntity.class,
                        getSession().createQuery( "from PersonEntity" )
                )
        );
    }
}
