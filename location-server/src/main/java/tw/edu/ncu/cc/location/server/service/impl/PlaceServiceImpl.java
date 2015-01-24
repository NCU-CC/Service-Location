package tw.edu.ncu.cc.location.server.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.service.PlaceService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                        "WHERE p.type = :placeType " +
                        "ORDER BY p.chineseName", PlaceEntity.class )
                .setParameter( "placeType", type )
                .getResultList();
    }

    @Override
    public List< PlaceEntity > getPlacesToBeIndexed( int offset, int max ) {
        return getEntityManager()
                .createQuery(
                        "SELECT p FROM PlaceEntity p " +
                        "WHERE p.type = 'RESTAURANT' " +
                        "OR p.type = 'SPORT_RECREATION' " +
                        "OR p.type = 'ADMINISTRATION'" +
                        "OR p.type = 'RESEARCH' " +
                        "OR p.type = 'DORMITORY' " +
                        "OR p.type = 'OTHER' ", PlaceEntity.class )
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
        List< UnitEntity > units = new ArrayList<>();
        for ( PlaceUnitEntity placeUnit : placeUnits ) {
            if( placeUnit.getUnit() == null ) {
                UnitEntity unitEntity = new UnitEntity();
                unitEntity.setChineseName( placeUnit.getUnitName() );
                units.add( unitEntity );
            } else {
                units.add( placeUnit.getUnit() );
            }
        }
        Collections.sort( units, new Comparator< UnitEntity >() {
            @Override
            public int compare( UnitEntity u1, UnitEntity u2 ) {
                return u1.getChineseName().compareTo( u2.getChineseName() );
            }
        } );
        return units;
    }

}
