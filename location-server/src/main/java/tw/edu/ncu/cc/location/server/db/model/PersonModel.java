package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.server.db.data.PersonEntity;

import java.util.Set;

public interface PersonModel {
    public void persistPeople( PersonEntity... personEntities );
    public PersonEntity getPerson( Integer id );
    public PersonEntity getPerson( String chineseName );
    public Set<PersonEntity> getPeople( String chineseName );
}
