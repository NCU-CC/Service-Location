package tw.edu.ncu.cc.location.server.db.model.base;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;

import javax.inject.Inject;
import java.io.IOException;

public abstract class LuceneAccessTool {

    private IndexSearcher indexSearcher;
    private IndexWriter   indexWriter;

    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }

    @Inject
    public void setIndexSearcher( IndexSearcher indexSearcher ) {
        this.indexSearcher = indexSearcher;
    }

    public IndexWriter getIndexWriter() {
        return indexWriter;
    }

    @Inject
    public void setIndexWriter( IndexWriter indexWriter ) {
        this.indexWriter = indexWriter;
    }

    protected void persistDocuments( String key, Document... documents ) throws IOException {
        IndexWriter writer = getIndexWriter();
        for( Document document : documents ) {
            writer.updateDocument( new Term( key, document.get( key ) ), document );
        }
        writer.commit();
    }

    protected Document[] searchDocuments( String key, String value, int limit ) throws IOException, ParseException {
        IndexSearcher searcher = getIndexSearcher();
        ScoreDoc[] scoreDocs = searcher.search( buildQuery( key, value ), limit ).scoreDocs;
        Document[] documents = new Document[ scoreDocs.length ];
        for( int i = 0; i < documents.length; i ++ ) {
            documents[ i ] = searcher.doc( scoreDocs[ i ].doc );
        }
        return documents;
    }

    private Query buildQuery( String key, String value ) throws ParseException {
        return new QueryParser( key, new SmartChineseAnalyzer() ).parse( value );
    }

}
