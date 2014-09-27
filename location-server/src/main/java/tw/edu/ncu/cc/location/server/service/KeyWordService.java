package tw.edu.ncu.cc.location.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "keyword" )
public class KeyWordService {

    @GET
    @Path( "{word}" )
    @Produces("application/json;charset=utf-8")
    public String getKeyWord( @PathParam( "word" ) String word ) {
        return "You have entered:" + word;
    }

}
