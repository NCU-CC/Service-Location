package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tw.edu.ncu.cc.location.server.entity.PersonEntity
import tw.edu.ncu.cc.location.server.repository.PersonRepository

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    def PersonRepository personRepository

    @Override
    PersonEntity findByPortalId( String portalId ) {
        personRepository.findByPortalId( portalId )
    }

    @Override
    public List< PersonEntity > getPeopleByChineseName( String chineseName ) {
        personRepository.findByChineseName( chineseName )
    }

}
