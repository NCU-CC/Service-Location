package tw.edu.ncu.cc.location.server.entity

import com.vividsolutions.jts.geom.Point
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table;

@Entity
@Table( name = "Unit" )
public class UnitEntity {

    @Id
    @Column( name = "unit_no" )
    def String unitCode;

    @Column( name = "cname" )
    def String chineseName;

    @Column( name = "ename" )
    def String englishName;

    @Column( name = "short_name" )
    def String shortName;

    @Column( name = "full_name" )
    def String fullName;

    @Column( name = "url" )
    def String url;

    @Column( name = "location" )
    @Type( type = "org.hibernate.spatial.GeometryType" )
    def Point location;

}
