package tw.edu.ncu.cc.location.server.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.service.UnitService;

import java.util.List;

@Service
public class UnitServiceImpl extends EntityManagerContainer implements UnitService {

    @Override
    public List< UnitEntity > getUnits( String fullName ) {
        return getEntityManager()
                .createQuery(
                        "SELECT u FROM UnitEntity u " +
                        "WHERE u.fullName = :fname ", UnitEntity.class )
                .setParameter( "fname", fullName )
                .getResultList();
    }

    @Override
    public List< UnitEntity > getUnits( int offset, int max ) {
        return getEntityManager()
                .createQuery( "SELECT u FROM UnitEntity u ", UnitEntity.class )
                .setFirstResult( offset )
                .setMaxResults( max )
                .getResultList();
    }

}
