package tw.edu.ncu.cc.location.server.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ncu.cc.location.server.entity.PersonEntity;
import tw.edu.ncu.cc.location.server.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl extends EntityManagerContainer implements PersonService {

    @Override
    public List< PersonEntity > getPeople( String chineseName ) {
        return getEntityManager()
                .createQuery(
                        "SELECT p from PersonEntity p " +
                        "LEFT JOIN FETCH p.primaryUnit " +
                        "LEFT JOIN FETCH p.secondaryUnit " +
                        "WHERE p.chineseName = :cname ", PersonEntity.class )
                .setParameter( "cname", chineseName )
                .getResultList();
    }

    @Override
    public List< PersonEntity > getPeopleToBeIndexed( int offset, int max ) {
        return getEntityManager()
                .createQuery( "SELECT p FROM PersonEntity p", PersonEntity.class )
                .setFirstResult( offset )
                .setMaxResults( max )
                .getResultList();
    }

}
