package tw.edu.ncu.cc.location.db.data;

import javax.persistence.*;

@Entity
public class Location {

    @Id @GeneratedValue
    private Integer id;

    @Enumerated( EnumType.STRING )
    @Column( nullable = false )
    private LocationType type;

    @Column( length = 80 )
    private String name;

    @Column( nullable = false )
    private Double longitude;

    @Column( nullable = false )
    private Double latitude;

    @Column( length = 40 )
    private String pictureName;

    public Location() {}
    public Location( LocationType type, Double longitude, Double latitude ) {
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public LocationType getType() {
        return type;
    }

    public void setType( LocationType type ) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude( Double longitude ) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude( Double latitude ) {
        this.latitude = latitude;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName( String pictureName ) {
        this.pictureName = pictureName;
    }
}
