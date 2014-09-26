package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.server.db.data.Place;
import tw.edu.ncu.cc.location.server.db.data.PlaceType;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.db.model.base.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class PlaceModelImpl extends HibernateAccessTool implements PlaceModel {

    @Override
    public void persistPlace( Place... places ) {
        persistObjects( ( Object[] ) places );
    }

    @Override
    public Place getPlace( Integer id ) {
        return ( Place ) getObject( id, Place.class );
    }

    @Override
    public Set<Place> getPlaces( String name ) {
        return new HashSet<>(
                getObjects( Place.class, String.format( "name = '%s'", name ) )
        );
    }

    @Override
    public Set<Place> getPlaces( PlaceType type ) {
        return new HashSet<>(
                getObjects( Place.class, String.format( "type = '%s'", type.toString() )  )
        );
    }

}
