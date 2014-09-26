package tw.edu.ncu.cc.location.data.unit;


@SuppressWarnings( "unused" )
public class UnitBase {

    private Double lng;
    private Double lat;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

}
