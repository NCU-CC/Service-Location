package tw.edu.ncu.cc.location.db.model.abstracts;

import tw.edu.ncu.cc.location.db.data.Location;
import tw.edu.ncu.cc.location.db.data.LocationType;

import java.util.Set;

public interface LocationModel {
    public void persistLocation( Location...locations );
    public Location getLocation( Integer id );
    public Set<Location> getLocations( String name );
    public Set<Location> getLocations( LocationType type );
}
