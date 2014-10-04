package tw.edu.ncu.cc.location.client.core;

import tw.edu.ncu.cc.location.client.converter.ResponseConverter;
import tw.edu.ncu.cc.location.client.core.abstracts.LocationClient;
import tw.edu.ncu.cc.location.client.core.abstracts.LocationConfig;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.person.PersonWrapper;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.place.PlaceWrapper;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.unit.UnitWrapper;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Set;

public class NCULocationClient implements LocationClient {

    private WebTarget target;

    public NCULocationClient( LocationConfig config ) {
        target = ClientBuilder.newClient().target( config.getServerAddress() );
    }

    @Override
    public Set<Place> getPlaces( String placeName ) {
        PlaceWrapper response = target.path( "/place/name/" + placeName )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( PlaceWrapper.class );

        return ResponseConverter.convert( response );
    }

    @Override
    public Set<Place> getPlaces( PlaceType placeType ) {
        PlaceWrapper response = target.path( "/place/type/" + placeType.value() )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( PlaceWrapper.class );

        return ResponseConverter.convert( response );
    }

    @Override
    public Set<Person> getPeople( String peopleName ) {
        PersonWrapper response = target.path( "/person/name/" + peopleName )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( PersonWrapper.class );

        return ResponseConverter.convert( response );
    }

    @Override
    public Set<Unit> getUnits( String unitName ) {
        UnitWrapper response = target.path( "/unit/name/" + unitName )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( UnitWrapper.class );

        return ResponseConverter.convert( response );
    }

}
