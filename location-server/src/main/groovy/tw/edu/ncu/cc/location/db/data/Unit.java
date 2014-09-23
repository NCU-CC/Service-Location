package tw.edu.ncu.cc.location.db.data;

import javax.persistence.*;

@Entity
public class Unit {

    @Id @GeneratedValue
    private Integer id;

    @Enumerated( EnumType.STRING )
    @Column( nullable = false )
    private UnitType type;

    @Column( length = 80, nullable = false )
    private String name;

    @Column( nullable = false )
    private Double longitude;

    @Column( nullable = false )
    private Double latitude;

    @Column( length = 25 )
    private String telephone;

    private String url;

    public Unit() {}
    public Unit( String name, UnitType type,
                 Double longitude, Double latitude ) {
        this.name = name;
        this.type = type;
        this.longitude = longitude;
        this.latitude  = latitude;
    }


    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public UnitType getType() {
        return type;
    }

    public void setType( UnitType type ) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }
}
