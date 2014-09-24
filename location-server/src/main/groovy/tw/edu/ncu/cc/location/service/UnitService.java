package tw.edu.ncu.cc.location.service;

import tw.edu.ncu.cc.location.db.data.UnitType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path( "unit" )
public class UnitService {

    @GET
    @Path( "type/{type}" )
    public String getUnitByType( @PathParam( "type" ) UnitType type ) {
        return "";
    }

    @GET
    @Path( "name/{name}" )
    public String getUnitByName( @PathParam( "name" ) String name ) {
        return "";
    }


}
