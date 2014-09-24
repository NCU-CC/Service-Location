package tw.edu.ncu.cc.location.service;

import tw.edu.ncu.cc.location.db.data.LocationType;
import tw.edu.ncu.cc.location.db.model.abstracts.LocationModel;
import tw.edu.ncu.cc.location.response.location.LocationNoNameWrapper;
import tw.edu.ncu.cc.location.response.location.LocationNoTypeWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "location" )
public class LocationService {

    @Inject LocationModel locationModel;

    @GET
    @Path( "type/{type}" )
    @Produces("application/json")
    public LocationNoTypeWrapper getLocationByType( @PathParam( "type" ) LocationType type ) {
        return new LocationNoTypeWrapper( locationModel.getLocations( type ) );
    }

    @GET
    @Path( "name/{name}" )
    @Produces("application/json")
    public LocationNoNameWrapper getLocationByName( @PathParam( "name" ) String name ) {
        return new LocationNoNameWrapper( locationModel.getLocations( name ) );
    }

}
