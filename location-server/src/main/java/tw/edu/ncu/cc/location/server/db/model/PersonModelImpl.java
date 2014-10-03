package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.server.db.model.base.HibernateAccessTool;

public class PersonModelImpl extends HibernateAccessTool implements PersonModel {

    @Override
    public void persistPersons( PersonEntity... personEntities ) {
        persistObjects( ( Object[] ) personEntities );
    }

    @Override
    public PersonEntity getPerson( Integer id ) {
        return ( PersonEntity ) getObject( id, PersonEntity.class );
    }

    @Override
    public PersonEntity getPerson( String chineseName ) {
        return getObject( PersonEntity.class, String.format( "cname = '%s'", chineseName ) );
    }

}
