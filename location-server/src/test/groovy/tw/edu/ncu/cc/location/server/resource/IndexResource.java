package tw.edu.ncu.cc.location.server.resource;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.junit.rules.ExternalResource;
import tw.edu.ncu.cc.location.server.factory.IndexSearcherFactory;
import tw.edu.ncu.cc.location.server.factory.IndexWriterFactory;
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig;

import java.io.IOException;

public class IndexResource extends ExternalResource {

    private IndexWriter writer;
    private IndexWriterFactory writerFactory;
    private IndexSearcherFactory searcherFactory;

    public IndexResource( String indexPath ) {
        LuceneConfig config = new LuceneConfig( indexPath );
        writerFactory   = new IndexWriterFactory  ( config );
        searcherFactory = new IndexSearcherFactory( config );
    }

    @Override
    protected void before() throws Throwable {
        writer = writerFactory.provide();
        try {
            writer.deleteAll();
        } catch ( IOException e ) {
            throw new RuntimeException( "cannot delete indexes", e );
        }
    }

    @Override
    protected void after() {
        try {
            writer.close();
        } catch ( IOException e ) {
            throw new RuntimeException( "cannot close writer", e );
        }
    }

    public IndexWriter getIndexWriter() {
        return writer;
    }

    public IndexSearcher getIndexSearcher() {
        return searcherFactory.provide();
    }
}
