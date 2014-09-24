package tw.edu.ncu.cc.location.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Unit {

    @Id @GeneratedValue
    private Integer id;

    @Column( length = 80, nullable = false )
    private String name;

    @Column( nullable = false )
    private Double longitude;

    @Column( nullable = false )
    private Double latitude;

    private String url;

    public Unit() {}
    public Unit( String name, Double longitude, Double latitude ) {
        this.name = name;
        this.longitude = longitude;
        this.latitude  = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }
}
