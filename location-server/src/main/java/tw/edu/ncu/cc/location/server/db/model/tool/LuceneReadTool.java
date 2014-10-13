package tw.edu.ncu.cc.location.server.db.model.tool;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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
        ScoreDoc[] scoreDocs = sortDocs( searcher.search( buildQuery( key, value ), limit ) );
        Document[] documents = new Document[ scoreDocs.length ];
        for( int i = 0; i < documents.length; i ++ ) {
            documents[ i ] = searcher.doc( scoreDocs[ i ].doc );
        }
        return documents;
    }

    private ScoreDoc[] sortDocs( TopDocs topDocs ) {
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        Arrays.sort( scoreDocs, new Comparator<ScoreDoc>() {
            @Override
            public int compare( ScoreDoc scoreDoc1, ScoreDoc scoreDoc2 ) {
                return ( int ) ( scoreDoc2.score - scoreDoc1.score );
            }
        } );
        return scoreDocs;
    }

    private Query buildQuery( String key, String value ) throws ParseException {
        return new QueryParser( key, new SmartChineseAnalyzer() ).parse( value );
    }
}
