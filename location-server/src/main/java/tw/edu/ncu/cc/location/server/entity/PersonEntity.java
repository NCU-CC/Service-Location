package tw.edu.ncu.cc.location.server.entity;

import javax.persistence.*;

@Entity
@Table( name = "Person" )
public class PersonEntity {

    private int id;
    private String personalNo;
    private String chineseName;
    private String englishName;
    private String title;
    private UnitEntity primaryUnit;
    private UnitEntity secondaryUnit;
    private String officePhone;

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
    @Column( name = "personal_no" )
    public String getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo( String personalNo ) {
        this.personalNo = personalNo;
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
    @Column( name = "title" )
    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "primary_unit_no" )
    public UnitEntity getPrimaryUnit() {
        return primaryUnit;
    }

    public void setPrimaryUnit( UnitEntity primaryUnit ) {
        this.primaryUnit = primaryUnit;
    }

    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "secondary_unit_no" )
    public UnitEntity getSecondaryUnit() {
        return secondaryUnit;
    }

    public void setSecondaryUnit( UnitEntity secondaryUnit ) {
        this.secondaryUnit = secondaryUnit;
    }

    @Basic
    @Column( name = "office_phone" )
    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone( String officePhone ) {
        this.officePhone = officePhone;
    }

}
