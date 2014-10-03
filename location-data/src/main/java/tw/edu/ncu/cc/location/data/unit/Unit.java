package tw.edu.ncu.cc.location.data.unit;

import tw.edu.ncu.cc.location.data.location.Position;

public class Unit {

    private String unitCode;
    private String chineseName;
    private String englishName;
    private String shortName;
    private String fullName;
    private String url;
    private Position location;

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode( String unitCode ) {
        this.unitCode = unitCode;
    }

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName( String shortName ) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName( String fullName ) {
        this.fullName = fullName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation( Position location ) {
        this.location = location;
    }
}
