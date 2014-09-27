package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.place.PlaceNoNameWrapper;
import tw.edu.ncu.cc.location.data.place.PlaceNoTypeWrapper;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.server.db.data.Place;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.response.place.ServerPlaceNoNameWrapper;
import tw.edu.ncu.cc.location.server.response.place.ServerPlaceNoTypeWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashSet;

@Path( "place" )
public class PlaceService {

    @Inject PlaceModel placeModel;

    @GET
    @Path( "type/{type}" )
    @Produces("application/json")
    public PlaceNoTypeWrapper getPlaceByType( @PathParam("type") String type ) {
        try{
            return new ServerPlaceNoTypeWrapper( placeModel.getPlaces( PlaceType.fromValue( type ) ) );
        } catch ( IllegalArgumentException ignore ) {
            return new ServerPlaceNoTypeWrapper( new HashSet<Place>() );
        }
    }

    @GET
    @Path( "name/{name}" )
    @Produces("application/json")
    public PlaceNoNameWrapper getPlaceByName( @PathParam("name") String name ) {
        return new ServerPlaceNoNameWrapper( placeModel.getPlaces( name ) );
    }

}
