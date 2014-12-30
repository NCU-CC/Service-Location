package tw.edu.ncu.cc.location.client.jersey;

import tw.edu.ncu.cc.location.client.tool.SyncLocationClient;
import tw.edu.ncu.cc.location.client.tool.config.LocationConfig;
import tw.edu.ncu.cc.location.client.tool.converter.ResponseConverter;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class NCUSynLocationClient implements SyncLocationClient {

    private WebTarget target;

    public NCUSynLocationClient( LocationConfig config ) {
        target = ClientBuilder.newClient().target( config.getServerAddress() );
    }

    @Override
    public List<Place> getPlaces( String placeName ) {
        return ResponseConverter.convert(
                target.path( "/place/name/" + placeName )
                        .request( MediaType.APPLICATION_JSON_TYPE )
                        .get( new GenericType< ResultWrapper<Place> >() {} )
        );
    }

    @Override
    public List<Place> getPlaces( PlaceType placeType ) {
        return ResponseConverter.convert(
                target.path( "/place/type/" + placeType.value() )
                        .request( MediaType.APPLICATION_JSON_TYPE )
                        .get( new GenericType< ResultWrapper<Place> >() {} )
        );
    }

    @Override
    public List<Person> getPeople( String peopleName ) {
        return ResponseConverter.convert(
                target.path( "/person/name/" + peopleName )
                        .request( MediaType.APPLICATION_JSON_TYPE )
                        .get( new GenericType< ResultWrapper<Person> >() {} )
        );
    }

    @Override
    public List<Unit> getUnits( String unitName ) {
        return ResponseConverter.convert(
                target.path( "/unit/name/" + unitName )
                        .request( MediaType.APPLICATION_JSON_TYPE )
                        .get( new GenericType< ResultWrapper<Unit> >() {} )
        );
    }

    @Override
    public List<Word> getWords( String keyword ) {
        return ResponseConverter.convert(
                target.path( "/keyword/" + keyword )
                    .request( MediaType.APPLICATION_JSON_TYPE )
                    .get( new GenericType< ResultWrapper<Word> >() {} )
        );
    }

}
