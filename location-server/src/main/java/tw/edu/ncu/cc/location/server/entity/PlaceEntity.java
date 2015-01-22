package tw.edu.ncu.cc.location.server.entity;

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

}
