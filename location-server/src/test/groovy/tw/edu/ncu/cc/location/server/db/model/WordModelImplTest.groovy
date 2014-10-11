package tw.edu.ncu.cc.location.server.db.model

import org.junit.Rule
import spock.lang.Specification
import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.data.keyword.WordType
import tw.edu.ncu.cc.location.server.lucene.LuceneWord
import tw.edu.ncu.cc.location.server.resource.IndexResource

class WordModelImplTest extends Specification {

    @Rule
    IndexResource indexResource = new IndexResource()

    WordPersistModelImpl wordPersistModel
    WordRetriveModelImpl wordRetriveModel

    def setup() {
        wordPersistModel = new WordPersistModelImpl()
        wordRetriveModel = new WordRetriveModelImpl()
    }

    def "they can handle word persistence, reader must init after write done"() {
        given:
            def word1 = new LuceneWord( "第一組 group one", "總務處-第一組", WordType.UNIT )
            def word2 = new LuceneWord( "第一組 group one", "教務處-第一組", WordType.UNIT )
        and:
            wordPersistModel.setIndexWriter( indexResource.getIndexWriter() )
            wordPersistModel.persistWords( word1, word2 )
        when:
            wordRetriveModel.setIndexSearcher( indexResource.getIndexSearcher() )
        then:
            wordRetriveModel.getWords( "第一組" ).contains( new Word( "總務處-第一組", WordType.UNIT ) )
            wordRetriveModel.getWords( "group one" ).contains( new Word( "教務處-第一組", WordType.UNIT ) )
    }

    def "it will not get latest data if read and write are called on same instance"() {
        expect: true
    }

}
