package tw.edu.ncu.cc.location.response.location;

import tw.edu.ncu.cc.location.db.data.Location;

@SuppressWarnings( "unused" )
public class LocationNoName extends LocationBase {

    private String type;

    public LocationNoName( Location location ) {
        super( location );
        setType( location.getType().toString() );
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }
}
