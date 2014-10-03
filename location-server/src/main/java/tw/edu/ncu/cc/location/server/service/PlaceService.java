package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.place.PlaceWrapper;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.response.ServerPlaceWrapper;

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
    @Produces("application/json;charset=utf-8")
    public PlaceWrapper getPlaceByType( @PathParam("type") String type ) {
        try{
            return new ServerPlaceWrapper( placeModel.getPlaces( PlaceType.fromValue( type ) ) );
        } catch ( IllegalArgumentException ignore ) {
            return new ServerPlaceWrapper( new HashSet<PlaceEntity>() );
        }
    }

    @GET
    @Path( "name/{name}" )
    @Produces("application/json;charset=utf-8")
    public PlaceWrapper getPlaceByName( @PathParam("name") String name ) {
        return new ServerPlaceWrapper( placeModel.getPlaces( name ) );
    }

}
