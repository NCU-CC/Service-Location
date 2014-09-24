package tw.edu.ncu.cc.location.db.model;

import tw.edu.ncu.cc.location.db.data.Location;
import tw.edu.ncu.cc.location.db.data.LocationType;
import tw.edu.ncu.cc.location.db.model.abstracts.LocationModel;
import tw.edu.ncu.cc.location.db.model.base.HibernateAccessTool;

import java.util.HashSet;
import java.util.Set;

public class LocationModelImpl extends HibernateAccessTool implements LocationModel {

    @Override
    public void persistLocation( Location... locations ) {
        persistObjects( ( Object[] ) locations );
    }

    @Override
    public Location getLocation( Integer id ) {
        return ( Location ) getObject( id, Location.class );
    }

    @Override
    public Set<Location> getLocations( String name ) {
        return new HashSet<>(
                getObjects( Location.class, String.format( "name = '%s'", name ) )
        );
    }

    @Override
    public Set<Location> getLocations( LocationType type ) {
        return new HashSet<>(
                getObjects( Location.class, String.format( "type = '%s'", type.toString() )  )
        );
    }

}
