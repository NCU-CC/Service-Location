package tw.edu.ncu.cc.location.service;


import tw.edu.ncu.cc.location.db.model.abstracts.UnitModel;
import tw.edu.ncu.cc.location.response.unit.UnitBaseWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "unit" )
public class UnitService {

    @Inject UnitModel unitModel;

    @GET
    @Path( "name/{name}" )
    @Produces("application/json")
    public UnitBaseWrapper getUnitByName( @PathParam( "name" ) String name ) {
        return new UnitBaseWrapper( unitModel.getUnits( name ) );
    }

}
