package tw.edu.ncu.cc.location.server.db.model.base;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;

import javax.inject.Inject;
import java.io.IOException;

public abstract class LuceneReadTool {

    private IndexSearcher indexSearcher;

    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }

    @Inject
    public void setIndexSearcher( IndexSearcher indexSearcher ) {
        this.indexSearcher = indexSearcher;
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
