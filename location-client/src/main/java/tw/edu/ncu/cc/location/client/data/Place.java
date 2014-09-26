package tw.edu.ncu.cc.location.client.data;

public class Place extends Location {

    private PlaceType type;
    private String pictureName;

    public PlaceType getType() {
        return type;
    }

    public void setType( PlaceType type ) {
        this.type = type;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName( String pictureName ) {
        this.pictureName = pictureName;
    }

}
