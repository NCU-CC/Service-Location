package tw.edu.ncu.cc.location.server.entity;

import javax.persistence.*;

@Entity
@Table( name = "Person" )
public class PersonEntity {

    @Id
    @GeneratedValue
    def Integer id;

    @Column( name = "portal_id" )
    def String portalId;

    @Column( name = "personal_no" )
    def String personalNo;

    @Column( name = "cname" )
    def String chineseName;

    @Column( name = "ename" )
    def String englishName;

    @Column( name = "title" )
    def String title;

    @OneToOne
    @JoinColumn( name = "primary_unit_no" )
    def UnitEntity primaryUnit;

    @OneToOne
    @JoinColumn( name = "secondary_unit_no" )
    def UnitEntity secondaryUnit;

    @Column( name = "office_phone" )
    def String officePhone;

}
