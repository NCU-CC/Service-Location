package tw.edu.ncu.cc.location.server.db.model.tool;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import javax.inject.Inject;
import java.io.IOException;

public abstract class LuceneWriteTool {

    private IndexWriter   indexWriter;

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

    protected void deleteAll() throws IOException {
        indexWriter.deleteAll();
    }

}
