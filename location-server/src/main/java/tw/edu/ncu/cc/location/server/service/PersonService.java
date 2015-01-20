package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.server.entity.PersonEntity;

import java.util.Set;

public interface PersonService {
    public void persistPeople( PersonEntity... personEntities );
    public PersonEntity getPerson( Integer id );
    public PersonEntity getPerson( String chineseName );
    public Set<PersonEntity> getPeople( String chineseName );
}
