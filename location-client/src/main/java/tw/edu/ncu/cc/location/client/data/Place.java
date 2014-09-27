package tw.edu.ncu.cc.location.client.data;

import tw.edu.ncu.cc.location.data.place.PlaceType;

public class Place extends Location {

    private PlaceType type;
    private String pictureName;

    public Place(){}
    public Place( String name, PlaceType type,
                  Double latitude, Double longitude, String pictureName ) {
        setName( name );
        setType( type );
        setLatitude ( latitude );
        setLongitude( longitude );
        setPictureName( pictureName );
    }

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
