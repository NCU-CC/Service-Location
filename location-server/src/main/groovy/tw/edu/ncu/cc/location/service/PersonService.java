package tw.edu.ncu.cc.location.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path( "person" )
public class PersonService {

    @GET
    @Path( "name/{name}" )
    public String getPersonLocationByName( @PathParam( "name" ) String name ) {
        return "";
    }

}
