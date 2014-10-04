package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.person.PersonWrapper;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.server.response.ServerPersonWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "person" )
public class PersonService {

    @Inject PersonModel personModel;

    @GET
    @Path( "name/{name}" )
    @Produces("application/json;charset=utf-8")
    public PersonWrapper getPersonLocationByName( @PathParam( "name" ) String name ) {
        return new ServerPersonWrapper( personModel.getPeople( name ) );
    }

}
