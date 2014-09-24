package tw.edu.ncu.cc.location.service;

import tw.edu.ncu.cc.location.db.data.LocationType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path( "location" )
public class LocationService {

    @GET
    @Path( "type/{type}" )
    public String getLocationByType( @PathParam( "type" ) LocationType type ) {
        return "";
    }

    @GET
    @Path( "name/{name}" )
    public String getLocationByName( @PathParam( "name" ) String name ) {
        return "";
    }

}
