package tw.edu.ncu.cc.location.data.person;

import tw.edu.ncu.cc.location.data.unit.Unit;

public class Person {

    private String chineseName;
    private String englishName;
    private String title;
    private Unit primaryUnit;
    private Unit secondaryUnit;
    private String officePhone;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName( String chineseName ) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName( String englishName ) {
        this.englishName = englishName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public Unit getPrimaryUnit() {
        return primaryUnit;
    }

    public void setPrimaryUnit( Unit primaryUnit ) {
        this.primaryUnit = primaryUnit;
    }

    public Unit getSecondaryUnit() {
        return secondaryUnit;
    }

    public void setSecondaryUnit( Unit secondaryUnit ) {
        this.secondaryUnit = secondaryUnit;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone( String officePhone ) {
        this.officePhone = officePhone;
    }
}
