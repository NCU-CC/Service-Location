package tw.edu.ncu.cc.location.server.lucene

import spock.lang.Specification
import tw.edu.ncu.cc.location.data.keyword.WordType


class LuceneWordTest extends Specification {

    LuceneWord luceneWord

    def setup() {
        luceneWord = new LuceneWord()
    }

    def "it has property of index in String"() {
        when:
            luceneWord.setIndex( "word" )
        then:
            luceneWord.getIndex() == "word"
    }

    def "it has property of word in String"() {
        when:
            luceneWord.setWord( "word" )
        then:
            luceneWord.getWord() == "word"
    }

    def "it has property of type in WordType"() {
        when:
            luceneWord.setType( WordType.PERSON )
        then:
            luceneWord.getType() == WordType.PERSON
    }

    def "it can constrcut by other way"() {
        when:
            def word = new LuceneWord( "index", "word", WordType.PERSON )
        then:
            word.getIndex() == "index"
            word.getWord()  == "word"
            word.getType()  == WordType.PERSON
    }

}
