package tw.edu.ncu.cc.location.server.service

import tw.edu.ncu.cc.location.server.entity.PersonEntity

public interface PersonService {
    public List< PersonEntity > getPeopleByChineseName( String chineseName );
    public PersonEntity findByPortalId( String portalId )
}
