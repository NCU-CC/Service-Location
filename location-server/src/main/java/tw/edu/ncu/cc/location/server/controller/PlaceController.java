package tw.edu.ncu.cc.location.server.controller;

import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.response.ServerPlaceWrapper;
import tw.edu.ncu.cc.location.server.response.ServerUnitWrapper;
import tw.edu.ncu.cc.location.server.service.PlaceService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashSet;

@Path( "place" )
public class PlaceController {

    @Inject
    PlaceService placeService;

    @GET
    @Path( "type/{type}" )
    @Produces("application/json;charset=utf-8")
    public ResultWrapper<Place> getPlaceByType( @PathParam("type") String type ) {
        try{
            return new ServerPlaceWrapper( placeService.getPlaces( PlaceType.fromValue( type ) ) );
        } catch ( IllegalArgumentException ignore ) {
            return new ServerPlaceWrapper( new HashSet<PlaceEntity>() );
        }
    }

    @GET
    @Path( "name/{name}" )
    @Produces("application/json;charset=utf-8")
    public ResultWrapper<Place> getPlaceByName( @PathParam("name") String name ) {
        return new ServerPlaceWrapper( placeService.getPlaces( name ) );
    }

    @GET
    @Path( "name/{name}/units" )
    @Produces("application/json;charset=utf-8")
    public ResultWrapper<Unit> getPlaceUnits( @PathParam("name") String name ) {
        return new ServerUnitWrapper( placeService.getUnits( name ) );
    }

}
