package tw.edu.ncu.cc.location.server.db.model.impl;

import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.db.model.PlaceModel;
import tw.edu.ncu.cc.location.server.db.model.tool.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class PlaceModelImpl extends HibernateAccessTool implements PlaceModel {

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

}
