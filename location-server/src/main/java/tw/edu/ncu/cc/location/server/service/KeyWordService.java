package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.keyword.WordWrapper;
import tw.edu.ncu.cc.location.server.db.model.abstracts.WordRetriveModel;
import tw.edu.ncu.cc.location.server.response.ServerWordWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "keyword" )
public class KeyWordService {

    @Inject WordRetriveModel wordRetriveModel;

    @GET
    @Path( "{word}" )
    @Produces("application/json;charset=utf-8")
    public WordWrapper getKeyWord( @PathParam( "word" ) String word ) {
        return new ServerWordWrapper( wordRetriveModel.getWords( word ) );
    }

}
