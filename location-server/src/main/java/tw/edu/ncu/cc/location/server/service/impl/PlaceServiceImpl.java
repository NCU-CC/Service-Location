package tw.edu.ncu.cc.location.server.service.impl;

import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.service.PlaceService;
import tw.edu.ncu.cc.location.server.service.tool.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class PlaceServiceImpl extends HibernateAccessTool implements PlaceService {

    @Override
    public void persistPlace( PlaceEntity... placeEntities ) {
        persistObjects( ( Object[] ) placeEntities );
    }

    @Override
    public PlaceEntity getPlace( Integer id ) {
        return ( PlaceEntity ) getObject( id, PlaceEntity.class );
    }

    @Override
    public Set<PlaceEntity> getPlaces( String chineseName ) {
        return new HashSet<>(
                getObjects(
                        PlaceEntity.class,
                        getSession()
                                .createQuery( "from PlaceEntity where chineseName = :cname" )
                                .setString( "cname", chineseName )
                )
        );
    }

    @Override
    public Set<PlaceEntity> getPlaces( PlaceType type ) {
        return new HashSet<>(
                getObjects(
                        PlaceEntity.class,
                        getSession()
                                .createQuery( "from PlaceEntity where type = :typeOrdinal" )
                                .setString( "typeOrdinal", type.value() )
                )
        );
    }

    @Override
    public Set<UnitEntity> getUnits( String chineseName ) {
        PlaceEntity placeEntity = getObjects(
                PlaceEntity.class,
                getSession()
                        .createQuery( "from PlaceEntity where chineseName = :cname" )
                        .setString( "cname", chineseName )
        ).get( 0 );
        Set< UnitEntity > units = new HashSet<>();
        for ( PlaceUnitEntity placeUnit : placeEntity.getPlaceUnits() ) {
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
