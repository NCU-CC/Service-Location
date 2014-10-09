package tw.edu.ncu.cc.location.server.factory;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.glassfish.hk2.api.Factory;
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class IndexWriterFactory implements Factory<IndexWriter> {

    private LuceneConfig config;

    @Inject
    public IndexWriterFactory( LuceneConfig config ) {
        this.config = config;
    }

    @Override
    public IndexWriter provide() {
        try {
            return new IndexWriter(
                    FSDirectory.open( new File( config.getIndexFilePath() ) ),
                    new IndexWriterConfig( Version.LUCENE_4_10_0, new SmartChineseAnalyzer() )
            );
        } catch ( IOException e ) {
            throw new Error( "error on opening index files", e );
        }
    }

    @Override
    public void dispose( IndexWriter instance ) {
        try {
            instance.close();
        } catch ( IOException e ) {
            throw new RuntimeException( "index writer close failed", e );
        }
    }

}
