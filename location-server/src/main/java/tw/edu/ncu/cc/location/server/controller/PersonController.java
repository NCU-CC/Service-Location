package tw.edu.ncu.cc.location.server.controller;

import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.response.ServerPersonWrapper;
import tw.edu.ncu.cc.location.server.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "person" )
public class PersonController {

    @Inject
    PersonService personService;

    @GET
    @Path( "name/{name}" )
    @Produces("application/json;charset=utf-8")
    public ResultWrapper<Person> getPersonLocationByName( @PathParam( "name" ) String name ) {
        return new ServerPersonWrapper( personService.getPeople( name ) );
    }

}
