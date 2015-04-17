package tw.edu.ncu.cc.location.server.service.impl

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification
import tw.edu.ncu.cc.location.data.keyword.WordType
import tw.edu.ncu.cc.location.server.helper.data.LuceneWord
import tw.edu.ncu.cc.location.server.service.WordService

class WordServiceImplTest extends SpringSpecification {

    @Autowired
    private WordService wordService

    def cleanup() {
        wordService.clearAllWords()
    }

    def "it can index the given word"() {
        given:
            def word = new LuceneWord()
            word.setIndex( "中央研究院-核心部門" )
            word.setWord( "中央研究院" )
            word.setType( WordType.UNIT )
        when:
            wordService.persistWords( word )
        then:
            wordService.getWords( "中央" ).size() != 0
    }

    def "it can clear all indexed words"() {
        given:
            wordService.persistWords(
                    new LuceneWord( "關鍵字", "實際值", WordType.UNIT)
            )
        when:
            wordService.clearAllWords()
        then:
            wordService.getWords( "關鍵字" ).size() == 0
    }

}
