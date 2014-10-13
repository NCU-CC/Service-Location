package tw.edu.ncu.cc.location.server.service;


import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.db.model.UnitModel;
import tw.edu.ncu.cc.location.server.response.ServerUnitWrapper;

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
    @Produces("application/json;charset=utf-8")
    public ResultWrapper<Unit> getUnitByName( @PathParam( "name" ) String name ) {
        return new ServerUnitWrapper( unitModel.getUnits( name ) );
    }

}
