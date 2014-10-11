package tw.edu.ncu.cc.location.server.db.data;

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

    public PersonEntity() {}

    public PersonEntity( String personalNo, String chineseName, String title,
                         UnitEntity primaryUnit, UnitEntity secondaryUnit ) {
        this.personalNo = personalNo;
        this.chineseName = chineseName;
        this.title = title;
        this.primaryUnit = primaryUnit;
        this.secondaryUnit = secondaryUnit;
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

    @JoinColumn( name = "primary_unit_no" )
    @OneToOne( fetch = FetchType.LAZY )
//    @JoinColumn( name = "primary_unit_no", referencedColumnName = "unit_no" )
    public UnitEntity getPrimaryUnit() {
        return primaryUnit;
    }

    public void setPrimaryUnit( UnitEntity primaryUnit ) {
        this.primaryUnit = primaryUnit;
    }

    @JoinColumn( name = "secondary_unit_no" )
    @OneToOne( fetch = FetchType.LAZY )
//    @JoinColumn( name = "secondary_unit_no", referencedColumnName = "unit_no" )
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        PersonEntity that = ( PersonEntity ) o;

        if ( id != that.id ) return false;
        if ( chineseName != null ? !chineseName.equals( that.chineseName ) : that.chineseName != null ) return false;
        if ( englishName != null ? !englishName.equals( that.englishName ) : that.englishName != null ) return false;
        if ( officePhone != null ? !officePhone.equals( that.officePhone ) : that.officePhone != null ) return false;
        if ( personalNo != null ? !personalNo.equals( that.personalNo ) : that.personalNo != null ) return false;
        if ( primaryUnit != null ? !primaryUnit.equals( that.primaryUnit ) : that.primaryUnit != null )
            return false;
        if ( secondaryUnit != null ? !secondaryUnit.equals( that.secondaryUnit ) : that.secondaryUnit != null )
            return false;
        if ( title != null ? !title.equals( that.title ) : that.title != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ( personalNo != null ? personalNo.hashCode() : 0 );
        result = 31 * result + ( chineseName != null ? chineseName.hashCode() : 0 );
        result = 31 * result + ( englishName != null ? englishName.hashCode() : 0 );
        result = 31 * result + ( title != null ? title.hashCode() : 0 );
        result = 31 * result + ( primaryUnit != null ? primaryUnit.hashCode() : 0 );
        result = 31 * result + ( secondaryUnit != null ? secondaryUnit.hashCode() : 0 );
        result = 31 * result + ( officePhone != null ? officePhone.hashCode() : 0 );
        return result;
    }
}
