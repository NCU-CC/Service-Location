package tw.edu.ncu.cc.location.server.db.data;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table( name = "Unit" )
//@Table( name = "Unit", schema = "", catalog = "location" )
public class UnitEntity {

    private String unitCode;
    private String chineseName;
    private String englishName;
    private String shortName;
    private String fullName;
    private String url;
    private Point location;

    public UnitEntity() {}

    public UnitEntity( String unitCode, String chineseName, String shortName, String fullName ) {
        this( unitCode, chineseName, shortName, fullName, null );
    }

    public UnitEntity( String unitCode, String chineseName, String shortName, String fullName, Point location ) {
        this.unitCode = unitCode;
        this.chineseName = chineseName;
        this.shortName = shortName;
        this.fullName = fullName;
        this.location = location;
    }

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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        UnitEntity that = ( UnitEntity ) o;

        if ( chineseName != null ? !chineseName.equals( that.chineseName ) : that.chineseName != null ) return false;
        if ( englishName != null ? !englishName.equals( that.englishName ) : that.englishName != null ) return false;
        if ( fullName != null ? !fullName.equals( that.fullName ) : that.fullName != null ) return false;
        if ( location != null ? !location.equals( that.location ) : that.location != null ) return false;
        if ( shortName != null ? !shortName.equals( that.shortName ) : that.shortName != null ) return false;
        if ( unitCode != null ? !unitCode.equals( that.unitCode ) : that.unitCode != null ) return false;
        if ( url != null ? !url.equals( that.url ) : that.url != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ( unitCode != null ? unitCode.hashCode() : 0 );
        result = 31 * result + ( chineseName != null ? chineseName.hashCode() : 0 );
        result = 31 * result + ( englishName != null ? englishName.hashCode() : 0 );
        result = 31 * result + ( shortName != null ? shortName.hashCode() : 0 );
        result = 31 * result + ( fullName != null ? fullName.hashCode() : 0 );
        result = 31 * result + ( url != null ? url.hashCode() : 0 );
        result = 31 * result + ( location != null ? location.hashCode() : 0 );
        return result;
    }
}
