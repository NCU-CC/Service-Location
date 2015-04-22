package tw.edu.ncu.cc.location.server.entity

import com.vividsolutions.jts.geom.Point
import org.hibernate.annotations.Type
import tw.edu.ncu.cc.location.data.place.PlaceType

import javax.persistence.*

@Entity
@Table( name = "Place" )
public class PlaceEntity {

    @Id
    @GeneratedValue
    def Integer id;

    @Column( name = "cname" )
    def String chineseName;

    @Column( name = "ename" )
    def String englishName;

    @Column( name = "picture_name" )
    def String pictureName;

    @Column( name = "type" )
    @Enumerated( EnumType.STRING )
    def PlaceType type;

    @Column( name = "location" )
    @Type( type = "org.hibernate.spatial.GeometryType" )
    def Point location;

    @OneToMany( mappedBy = "place" )
    def List< PlaceUnitEntity > placeUnits;

}
