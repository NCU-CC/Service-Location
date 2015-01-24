package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.server.entity.PersonEntity;

import java.util.List;

public interface PersonService {
    public List< PersonEntity > getPeople( String chineseName );
    public List< PersonEntity > getPeopleToBeIndexed( int offset, int max );
}
