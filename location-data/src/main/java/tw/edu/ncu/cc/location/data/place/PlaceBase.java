package tw.edu.ncu.cc.location.data.place;


@SuppressWarnings( "unused" )
public class PlaceBase {

    private Double lng;
    private Double lat;
    private String picName;

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
