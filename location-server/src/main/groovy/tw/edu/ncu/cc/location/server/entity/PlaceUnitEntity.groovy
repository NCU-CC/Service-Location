package tw.edu.ncu.cc.location.server.entity;

import javax.persistence.*;

@Entity
@Table( name = "Building_Unit" )
public class PlaceUnitEntity {

    @Id
    @GeneratedValue
    def Integer id;

    @Column( name = "unit_name" )
    def String unitName;

    @ManyToOne
    @JoinColumn( name = "unit_no" )
    def UnitEntity unit;

    @ManyToOne
    @JoinColumn( name = "place_id" )
    def PlaceEntity place;

}
