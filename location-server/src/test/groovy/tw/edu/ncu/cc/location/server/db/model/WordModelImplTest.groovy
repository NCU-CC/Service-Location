package tw.edu.ncu.cc.location.server.db.model

import org.apache.lucene.document.Document
import org.apache.lucene.index.IndexWriter
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.data.keyword.WordType
import tw.edu.ncu.cc.location.server.factory.IndexSearcherFactory
import tw.edu.ncu.cc.location.server.factory.IndexWriterFactory
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig
import tw.edu.ncu.cc.location.server.lucene.LuceneWord


class WordModelImplTest extends Specification {

    private @Shared LuceneConfig config
    private @Shared IndexWriter indexWriter

    private WordModelImpl wordModel

    def setupSpec() {
        config = new LuceneConfig( "lucene/index/" )
        indexWriter = new IndexWriterFactory( config ).provide()
        indexWriter.addDocument( new Document( ) )
    }

    def cleanupSpec() {
        indexWriter.close()
    }

    def setup() {
        wordModel = new WordModelImpl()
        wordModel.setIndexWriter( indexWriter )
    }

    def cleanup() {
        indexWriter.deleteAll()
    }

    def "it can handle word persistence"() {
        given:
            def word1 = new LuceneWord( "第一組 group one", "總務處-第一組", WordType.UNIT )
            def word2 = new LuceneWord( "第一組 group one", "教務處-第一組", WordType.UNIT )
        when:
            wordModel.persistWords( word1, word2 )
        and:
            wordModel.setIndexSearcher( new IndexSearcherFactory( config ).provide() )
        then:
            wordModel.getWords( "第一組" ).contains( new Word( "總務處-第一組", WordType.UNIT ) )
            wordModel.getWords( "group one" ).contains( new Word( "教務處-第一組", WordType.UNIT ) )
    }

    def "it may not get latest data if read and write at same instance"() {
        expect: true
    }

}
