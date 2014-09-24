package tw.edu.ncu.cc.location.response.location;

import tw.edu.ncu.cc.location.db.data.Location;

@SuppressWarnings( "unused" )
public class LocationBase {

    private Double lng;
    private Double lat;
    private String picName;

    public LocationBase( Location location ) {
        setLng( location.getLongitude() );
        setLat( location.getLatitude() );
        setPicName( location.getPictureName() );
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
