package tw.edu.ncu.cc.location.server.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.server.exception.handler.APIExceptionHandler;
import tw.edu.ncu.cc.location.server.service.WordService;

import java.util.List;

@RestController
@RequestMapping( value = "keyword", method = RequestMethod.GET )
public class WordController extends APIExceptionHandler {

    private WordService wordService;
    private ConversionService conversionService;

    @Autowired
    public void setWordService( WordService wordService ) {
        this.wordService = wordService;
    }

    @Autowired
    public void setConversionService( ConversionService conversionService ) {
        this.conversionService = conversionService;
    }

    @RequestMapping( value = "{word}" )
    public Word[] getKeyWord( @PathVariable( "word" ) String word ) {
        return ( Word[] ) conversionService.convert(
                wordService.getWords( word ),
                TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( Word.class ) ),
                TypeDescriptor.array( TypeDescriptor.valueOf( Word.class ) )
        );
    }

}
