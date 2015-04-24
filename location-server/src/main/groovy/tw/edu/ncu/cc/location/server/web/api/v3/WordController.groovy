package tw.edu.ncu.cc.location.server.web.api.v3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.TypeDescriptor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.server.service.WordService

@RestController
@RequestMapping( value = "v3/search", method = RequestMethod.GET )
public class WordController {

    @Autowired
    def WordService wordService

    @Autowired
    def ConversionService conversionService

    @RequestMapping
    public ResponseEntity search( @RequestParam( value = "q", required = true ) String word,
                                  @RequestParam( value = "size", defaultValue = "3", required = false ) int size ) {
        new ResponseEntity<>(
                conversionService.convert(
                        wordService.getWordsByKeyword( word, size ),
                        TypeDescriptor.collection( List.class, TypeDescriptor.valueOf( Word.class ) ),
                        TypeDescriptor.array( TypeDescriptor.valueOf( Word.class ) )
                ),
                HttpStatus.OK
        )
    }

}
