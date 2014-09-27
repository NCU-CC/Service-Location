package tw.edu.ncu.cc.location.client.data;

public class Unit extends Location {

    private String url;

    public Unit() {}
    public Unit( String name, String url, Double latitude, Double longitude ) {
        setName( name );
        setUrl ( url );
        setLatitude ( latitude );
        setLongitude( longitude );
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

}
