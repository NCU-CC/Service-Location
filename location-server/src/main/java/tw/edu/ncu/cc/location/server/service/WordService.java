package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.db.model.WordRetriveModel;
import tw.edu.ncu.cc.location.server.response.ServerWordWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "keyword" )
public class WordService {

    @Inject WordRetriveModel wordRetriveModel;

    @GET
    @Path( "{word}" )
    @Produces("application/json;charset=utf-8")
    public ResultWrapper<Word> getKeyWord( @PathParam( "word" ) String word ) {
        return new ServerWordWrapper( wordRetriveModel.getWords( word ) );
    }

}
