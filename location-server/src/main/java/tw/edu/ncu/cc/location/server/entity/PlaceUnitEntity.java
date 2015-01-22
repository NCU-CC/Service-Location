package tw.edu.ncu.cc.location.server.entity;

import javax.persistence.*;

@Entity
@Table( name = "Building_Unit" )
public class PlaceUnitEntity {

    private int id;
    private String unitName;
    private UnitEntity unit;
    private PlaceEntity place;

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
    @Column( name = "unit_name" )
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName( String unitName ) {
        this.unitName = unitName;
    }

    @ManyToOne
    @JoinColumn( name = "unit_no" )
    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit( UnitEntity unit ) {
        this.unit = unit;
    }

    @ManyToOne
    @JoinColumn( name = "place_id" )
    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace( PlaceEntity place ) {
        this.place = place;
    }

}
