package tw.edu.ncu.cc.location.server.tool.convert;

import com.vividsolutions.jts.geom.Point;
import tw.edu.ncu.cc.location.data.location.Position;

public class Point_PositionConverter implements Converter< Point, Position > {
    @Override
    public Position convertFrom( Point point ) {
        Position position = new Position();
        position.setLat( point.getY() );
        position.setLng( point.getX() );
        return position;
    }
}
