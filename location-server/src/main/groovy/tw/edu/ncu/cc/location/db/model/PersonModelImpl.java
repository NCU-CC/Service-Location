package tw.edu.ncu.cc.location.db.model;

import tw.edu.ncu.cc.location.db.data.Person;
import tw.edu.ncu.cc.location.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.db.model.base.HibernateAccessTool;

public class PersonModelImpl extends HibernateAccessTool implements PersonModel {

    @Override
    public void persistPersons( Person... persons ) {
        persistObjects( ( Object[] ) persons );
    }

    @Override
    public Person getPerson( Integer id ) {
        return ( Person ) getObject( id, Person.class );
    }

    @Override
    public Person getPerson( String name ) {
        return getObject( Person.class, String.format( "name = '%s'", name ) );
    }

}
