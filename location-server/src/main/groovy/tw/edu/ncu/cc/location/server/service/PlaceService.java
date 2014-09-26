package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.server.db.data.PlaceType;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.response.place.PlaceNoNameWrapper;
import tw.edu.ncu.cc.location.server.response.place.PlaceNoTypeWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "place" )
public class PlaceService {

    @Inject PlaceModel placeModel;

    @GET
    @Path( "type/{type}" )
    @Produces("application/json")
    public PlaceNoTypeWrapper getPlaceByType( @PathParam("type") PlaceType type ) {
        return new PlaceNoTypeWrapper( placeModel.getPlaces( type ) );
    }

    @GET
    @Path( "name/{name}" )
    @Produces("application/json")
    public PlaceNoNameWrapper getPlaceByName( @PathParam("name") String name ) {
        return new PlaceNoNameWrapper( placeModel.getPlaces( name ) );
    }

}
