package tw.edu.ncu.cc.location.server.controller;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;
import tw.edu.ncu.cc.location.server.response.ServerWordWrapper;
import tw.edu.ncu.cc.location.server.service.WordRetriveService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "keyword" )
public class WordController {

    @Inject
    WordRetriveService wordRetriveService;

    @GET
    @Path( "{word}" )
    @Produces("application/json;charset=utf-8")
    public ResultWrapper<Word> getKeyWord( @PathParam( "word" ) String word ) {
        return new ServerWordWrapper( wordRetriveService.getWords( word ) );
    }

}
