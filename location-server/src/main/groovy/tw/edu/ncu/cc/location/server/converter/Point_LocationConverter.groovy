package tw.edu.ncu.cc.location.server.converter

import com.vividsolutions.jts.geom.Point
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tw.edu.ncu.cc.location.data.location.Location

@Component
public class Point_LocationConverter implements Converter< Point, Location > {

    @Override
    public Location convert( Point point ) {
        new Location(
            lat: point.y,
            lng: point.x
        )
    }

}
