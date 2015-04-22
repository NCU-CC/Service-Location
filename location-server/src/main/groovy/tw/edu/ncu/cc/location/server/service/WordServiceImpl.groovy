package tw.edu.ncu.cc.location.server.service

import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.document.TextField
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.index.Term
import org.apache.lucene.queryparser.classic.ParseException
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.Query
import org.apache.lucene.search.ScoreDoc
import org.apache.lucene.search.SearcherManager
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.util.Version
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.data.keyword.WordType

import javax.annotation.PreDestroy

@Service
public class WordServiceImpl implements WordService {

    private Analyzer analyzer
    private IndexWriter indexWriter
    private SearcherManager searcherManager

    @Autowired
    public WordServiceImpl( @Value( '${custom.lucene.index.path}' ) String luceneIndexPath ) throws IOException {
        analyzer = new SmartChineseAnalyzer()
        indexWriter = new IndexWriter(
                FSDirectory.open( new File( luceneIndexPath ) ),
                new IndexWriterConfig( Version.LUCENE_4_10_0, analyzer )
        )
        searcherManager = new SearcherManager( indexWriter, true, null )
    }

    @Override
    public void persistWords( Word... words ) {
        try {
            Document[] documents = new Document[words.length]
            for ( int i = 0; i < words.length; i++ ) {
                documents[ i ] = new Document()
                documents[ i ].add( new TextField( "index", words[ i ].getIndex(), Field.Store.YES ) )
                documents[ i ].add( new TextField( "word",  words[ i ].getWord(),  Field.Store.YES ) )
                documents[ i ].add( new TextField( "type",  words[ i ].getType().name(), Field.Store.YES ) )
            }
            persistDocuments( "word", documents )
        } catch ( IOException e ) {
            throw new RuntimeException( "word persist error", e )
        }
    }

    private void persistDocuments( String key, Document... documents ) throws IOException {
        for ( Document document : documents ) {
            indexWriter.updateDocument( new Term( key, document.get( key ) ), document )
        }
        indexWriter.commit()
    }

    @Override
    public void clearAllWords() {
        try {
            indexWriter.deleteAll()
        } catch ( IOException e ) {
            throw new RuntimeException( "cannot delete all indexes", e )
        }
    }

    @Override
    public List<Word> getWordsByKeyword( String keyword ) {
        try {
            List<Word> words = new LinkedList<>()
            for ( Document document : searchDocuments( "index", keyword, 3 ) ) {
                Word word = new Word()
                word.setIndex( document.get( "index" ) )
                word.setWord ( document.get( "word" ) )
                word.setType ( WordType.valueOf( document.get( "type" ) ) )
                words.add( word )
            }
            return words;
        } catch ( IOException | ParseException e ) {
            throw new RuntimeException( "read keyword error", e )
        }
    }

    private Document[] searchDocuments( String key, String value, int limit ) throws IOException, ParseException {

        searcherManager.maybeRefresh()
        IndexSearcher indexSearcher = searcherManager.acquire()

        try {
            ScoreDoc[] scoreDocs = indexSearcher.search( buildQuery( key, value ), limit ).scoreDocs
            Document[] documents = new Document[scoreDocs.length]
            for ( int i = 0; i < documents.length; i++ ) {
                documents[ i ] = indexSearcher.doc( scoreDocs[ i ].doc )
            }
            return documents
        } finally {
            searcherManager.release( indexSearcher )
        }
    }

    private Query buildQuery( String key, String value ) throws ParseException {
        return new QueryParser( key, analyzer ).parse( value )
    }

    @PreDestroy
    public void destroy() throws IOException {
        indexWriter.close()
        searcherManager.close()
    }

}
