package tw.edu.ncu.cc.location.server.db.data;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;
import tw.edu.ncu.cc.location.data.place.PlaceType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "Place" )
public class PlaceEntity {

    private int id;
    private String chineseName;
    private String englishName;
    private String pictureName;
    private PlaceType type;
    private Point location;
    private List< PlaceUnitEntity > placeUnits;

    public PlaceEntity() {}

    public PlaceEntity( PlaceType type, String chineseName ) {
        this( type, chineseName, null );
    }

    public PlaceEntity( PlaceType type, String chineseName, Point location ) {
        this.type = type;
        this.location = location;
        this.chineseName = chineseName;
    }

    @Id
    @GeneratedValue
    @Column( name = "id" )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
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
    @Column( name = "pictureName" )
    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName( String pictureName ) {
        this.pictureName = pictureName;
    }

    @Basic
    @Column( name = "type" )
    @Enumerated( EnumType.STRING )
    public PlaceType getType() {
        return type;
    }

    public void setType( PlaceType type ) {
        this.type = type;
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

    @OneToMany( mappedBy = "place" )
    public List< PlaceUnitEntity > getPlaceUnits() {
        return placeUnits;
    }

    public void setPlaceUnits( List< PlaceUnitEntity > placeUnits ) {
        this.placeUnits = placeUnits;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        PlaceEntity that = ( PlaceEntity ) o;

        if ( id != that.id ) return false;
        if ( chineseName != null ? !chineseName.equals( that.chineseName ) : that.chineseName != null ) return false;
        if ( englishName != null ? !englishName.equals( that.englishName ) : that.englishName != null ) return false;
        if ( location != null ? !location.equals( that.location ) : that.location != null ) return false;
        if ( pictureName != null ? !pictureName.equals( that.pictureName ) : that.pictureName != null ) return false;
        if ( placeUnits != null ? !placeUnits.equals( that.placeUnits ) : that.placeUnits != null ) return false;
        if ( type != that.type ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ( chineseName != null ? chineseName.hashCode() : 0 );
        result = 31 * result + ( englishName != null ? englishName.hashCode() : 0 );
        result = 31 * result + ( pictureName != null ? pictureName.hashCode() : 0 );
        result = 31 * result + ( type != null ? type.hashCode() : 0 );
        result = 31 * result + ( location != null ? location.hashCode() : 0 );
        result = 31 * result + ( placeUnits != null ? placeUnits.hashCode() : 0 );
        return result;
    }

}
