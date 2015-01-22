package tw.edu.ncu.cc.location.server.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.service.PlaceService;

import java.util.LinkedList;
import java.util.List;

@Service
public class PlaceServiceImpl extends EntityManagerContainer implements PlaceService {

    @Override
    public List< PlaceEntity > getPlaces( String chineseName ) {
        return getEntityManager()
                .createQuery(
                        "SELECT p FROM PlaceEntity p " +
                        "WHERE p.chineseName = :cname ", PlaceEntity.class )
                .setParameter( "cname", chineseName )
                .getResultList();
    }

    @Override
    public List< PlaceEntity > getPlaces( PlaceType type ) {
        return getEntityManager()
                .createQuery(
                        "SELECT p FROM PlaceEntity p " +
                        "WHERE p.type = :placeType ", PlaceEntity.class )
                .setParameter( "placeType", type )
                .getResultList();
    }

    @Override
    public List< PlaceEntity > getPlaces( int offset, int max ) {
        return getEntityManager()
                .createQuery( "SELECT p FROM PlaceEntity p ", PlaceEntity.class )
                .setFirstResult( offset )
                .setMaxResults( max )
                .getResultList();
    }

    @Override
    public List< UnitEntity > getUnits( String chineseName ) {
        List< PlaceUnitEntity > placeUnits = getEntityManager()
                .createQuery(
                        "SELECT pu FROM PlaceUnitEntity pu " +
                        "WHERE pu.place.chineseName = :cname ", PlaceUnitEntity.class )
                .setParameter( "cname", chineseName )
                .getResultList();
        List< UnitEntity > units = new LinkedList<>();
        for ( PlaceUnitEntity placeUnit : placeUnits ) {
            if( placeUnit.getUnit() == null ) {
                UnitEntity unitEntity = new UnitEntity();
                unitEntity.setChineseName( placeUnit.getUnitName() );
                units.add( unitEntity );
            } else {
                units.add( placeUnit.getUnit() );
            }
        }
        return units;
    }

}
