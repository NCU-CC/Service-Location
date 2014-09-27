package tw.edu.ncu.cc.location.client.core;

import tw.edu.ncu.cc.location.client.converter.ResponseConverter;
import tw.edu.ncu.cc.location.client.core.abstracts.LocationClient;
import tw.edu.ncu.cc.location.client.core.abstracts.LocationConfig;
import tw.edu.ncu.cc.location.client.data.Place;
import tw.edu.ncu.cc.location.client.data.Unit;
import tw.edu.ncu.cc.location.data.person.PersonUnitWrapper;
import tw.edu.ncu.cc.location.data.place.PlaceNoNameWrapper;
import tw.edu.ncu.cc.location.data.place.PlaceNoTypeWrapper;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.UnitBaseWrapper;

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
        PlaceNoNameWrapper response = target.path( "/place/name/" + placeName )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( PlaceNoNameWrapper.class );

        return ResponseConverter.convert( response );
    }

    @Override
    public Set<Place> getPlaces( PlaceType placeType ) {
        PlaceNoTypeWrapper response = target.path( "/place/type/" + placeType.value() )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( PlaceNoTypeWrapper.class );

        return ResponseConverter.convert( response );
    }

    @Override
    public Set<Unit> getPersonUnits( String personName ) {
        PersonUnitWrapper response = target.path( "/person/name/" + personName )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( PersonUnitWrapper.class );

        return ResponseConverter.convert( response );
    }

    @Override
    public Set<Unit> getUnits( String unitName ) {
        UnitBaseWrapper response = target.path( "/unit/name/" + unitName )
                .request( MediaType.APPLICATION_JSON_TYPE )
                .get( UnitBaseWrapper.class );

        return ResponseConverter.convert( response );
    }

}
