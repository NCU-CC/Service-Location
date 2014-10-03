package tw.edu.ncu.cc.location.data.place;

import tw.edu.ncu.cc.location.data.location.Position;

public class Place {

    private String chineseName;
    private String englishName;
    private String pictureName;
    private PlaceType type;
    private Position location;

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

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName( String pictureName ) {
        this.pictureName = pictureName;
    }

    public PlaceType getType() {
        return type;
    }

    public void setType( PlaceType type ) {
        this.type = type;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation( Position location ) {
        this.location = location;
    }
}
