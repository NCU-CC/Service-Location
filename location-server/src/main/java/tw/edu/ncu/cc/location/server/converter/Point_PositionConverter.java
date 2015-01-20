package tw.edu.ncu.cc.location.server.converter;

import com.vividsolutions.jts.geom.Point;
import tw.edu.ncu.cc.location.data.location.Location;

public class Point_PositionConverter implements Converter< Point, Location> {
    @Override
    public Location convertFrom( Point point ) {
        Location location = new Location();
        location.setLat( point.getY() );
        location.setLng( point.getX() );
        return location;
    }
}
