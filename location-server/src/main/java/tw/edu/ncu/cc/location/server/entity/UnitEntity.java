package tw.edu.ncu.cc.location.server.entity;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table( name = "Unit" )
public class UnitEntity {

    private String unitCode;
    private String chineseName;
    private String englishName;
    private String shortName;
    private String fullName;
    private String url;
    private Point location;

    @Id
    @Column( name = "unit_no" )
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode( String unitCode ) {
        this.unitCode = unitCode;
    }

    @Basic
    @Column( name = "cname" )
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName( String chineseName ) {
        this.chineseName = chineseName;
    }

    @Basic
    @Column( name = "ename" )
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName( String englishName ) {
        this.englishName = englishName;
    }

    @Basic
    @Column( name = "short_name" )
    public String getShortName() {
        return shortName;
    }

    public void setShortName( String shortName ) {
        this.shortName = shortName;
    }

    @Basic
    @Column( name = "full_name" )
    public String getFullName() {
        return fullName;
    }

    public void setFullName( String fullName ) {
        this.fullName = fullName;
    }

    @Basic
    @Column( name = "url" )
    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    @Basic
    @Column( name = "location" )
    @Type( type = "org.hibernate.spatial.GeometryType" )
    public Point getLocation() {
        return location;
    }

    public void setLocation( Point location ) {
        this.location = location;
    }

}
