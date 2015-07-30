package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification
import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.data.keyword.WordType

class WordServiceImplTest extends SpringSpecification {

    @Autowired
    private WordService wordService

    def cleanup() {
        wordService.clearAllWords()
    }

    def "it can index the given word"() {
        given:
            def word = new Word()
            word.setIndex( "中央研究院-核心部門" )
            word.setWord ( "中央研究院" )
            word.setType ( WordType.UNIT )
        when:
            wordService.persistWords( word )
        and:
            def words = wordService.getWordsByKeyword( "中央", 3 )
        then:
            words.size() != 0
        and:
            words[0].index == "中央研究院-核心部門"
            words[0].word  == "中央研究院"
            words[0].type  == WordType.UNIT
    }

    def "it can clear all indexed words"() {
        given:
            wordService.persistWords(
                    new Word(
                            index: "關鍵字",
                            word:"實際值",
                            type:WordType.UNIT
                    )
            )
        when:
            wordService.clearAllWords()
        then:
            wordService.getWordsByKeyword( "關鍵字", 3 ).size() == 0
    }

}
