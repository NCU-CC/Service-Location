package tw.edu.ncu.cc.location.server.lucene;

public class LuceneConfig {

    private String indexFilePath;

    public LuceneConfig() { }

    public LuceneConfig( String path ) {
        setIndexFilePath( path );
    }

    public String getIndexFilePath() {
        return indexFilePath;
    }

    public void setIndexFilePath( String indexFilePath ) {
        this.indexFilePath = indexFilePath;
    }

}
