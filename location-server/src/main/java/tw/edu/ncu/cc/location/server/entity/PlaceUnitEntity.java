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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        PlaceUnitEntity that = ( PlaceUnitEntity ) o;

        if ( id != that.id ) return false;
        if ( place != null ? !place.equals( that.place ) : that.place != null ) return false;
        if ( unit != null ? !unit.equals( that.unit ) : that.unit != null ) return false;
        if ( unitName != null ? !unitName.equals( that.unitName ) : that.unitName != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ( unitName != null ? unitName.hashCode() : 0 );
        result = 31 * result + ( unit != null ? unit.hashCode() : 0 );
        result = 31 * result + ( place != null ? place.hashCode() : 0 );
        return result;
    }

}
