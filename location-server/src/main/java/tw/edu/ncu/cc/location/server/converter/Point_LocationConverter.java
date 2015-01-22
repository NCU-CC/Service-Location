package tw.edu.ncu.cc.location.server.converter;

import com.vividsolutions.jts.geom.Point;
import org.springframework.core.convert.converter.Converter;
import tw.edu.ncu.cc.location.data.location.Location;

public class Point_LocationConverter implements Converter< Point, Location > {

    @Override
    public Location convert( Point point ) {
        Location location = new Location();
        location.setLat( point.getY() );
        location.setLng( point.getX() );
        return location;
    }

}
