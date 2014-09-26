package tw.edu.ncu.cc.location.server.response.place;

import tw.edu.ncu.cc.location.server.db.data.Place;

@SuppressWarnings( "unused" )
public class PlaceNoType extends PlaceBase {

    private String name;

    public PlaceNoType( Place place ) {
        super( place );
        setName( place.getName() );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
