package tw.edu.ncu.cc.location.server.response.place;

import tw.edu.ncu.cc.location.server.db.data.Place;

@SuppressWarnings( "unused" )
public class PlaceBase {

    private Double lng;
    private Double lat;
    private String picName;

    public PlaceBase( Place place ) {
        setLng( place.getLongitude() );
        setLat( place.getLatitude() );
        setPicName( place.getPictureName() );
    }

    public Double getLng() {
        return lng;
    }

    public void setLng( Double lng ) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat( Double lat ) {
        this.lat = lat;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName( String picName ) {
        this.picName = picName;
    }

}
