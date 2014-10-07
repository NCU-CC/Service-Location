package tw.edu.ncu.cc.location.server.factory;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.glassfish.hk2.api.Factory;
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class IndexSearcherFactory implements Factory<IndexSearcher> {

    private LuceneConfig config;
    private static DirectoryReader reader;
    private static IndexSearcher indexSearcher;

    @Inject
    public IndexSearcherFactory( LuceneConfig config ) {
        this.config = config;
    }

    @Override
    public IndexSearcher provide() {

        try {

            if( reader == null ) {
                reader = DirectoryReader.open(
                        FSDirectory.open( new File( config.getIndexFilePath() ) )
                );
                indexSearcher = new IndexSearcher( reader );
            } else {
                DirectoryReader newReader = DirectoryReader.openIfChanged( reader );
                if( newReader != null ) {
                    reader.close();
                    reader = newReader;
                    indexSearcher = new IndexSearcher( newReader );
                }
            }

            return indexSearcher;

        } catch ( IOException e ) {
            throw new Error( "error on reading index files", e );
        }
    }

    @Override
    public void dispose( IndexSearcher instance ) {
        //IndexSearcher will never be closed
    }

}
