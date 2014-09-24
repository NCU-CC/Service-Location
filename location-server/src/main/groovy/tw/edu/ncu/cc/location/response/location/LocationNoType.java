package tw.edu.ncu.cc.location.response.location;

import tw.edu.ncu.cc.location.db.data.Location;

@SuppressWarnings( "unused" )
public class LocationNoType extends LocationBase {

    private String name;

    public LocationNoType( Location location ) {
        super( location );
        setName( location.getName() );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
