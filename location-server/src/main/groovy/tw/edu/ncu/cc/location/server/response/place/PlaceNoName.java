package tw.edu.ncu.cc.location.server.response.place;

import tw.edu.ncu.cc.location.server.db.data.Place;

@SuppressWarnings( "unused" )
public class PlaceNoName extends PlaceBase {

    private String type;

    public PlaceNoName( Place place ) {
        super( place );
        setType( place.getType().toString() );
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }
}
