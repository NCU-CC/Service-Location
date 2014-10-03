package tw.edu.ncu.cc.location.server.db.model.abstracts;

import tw.edu.ncu.cc.location.server.db.data.PersonEntity;

public interface PersonModel {
    public void   persistPersons( PersonEntity... personEntities );
    public PersonEntity getPerson( Integer id );
    public PersonEntity getPerson( String chineseName );
}
