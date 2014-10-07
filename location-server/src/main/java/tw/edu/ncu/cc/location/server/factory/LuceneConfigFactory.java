package tw.edu.ncu.cc.location.server.factory;

import org.glassfish.hk2.api.Factory;
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig;

public class LuceneConfigFactory implements Factory<LuceneConfig> {

    @Override
    public LuceneConfig provide() {
        return new LuceneConfig( "location-server/lucene/index/" );
    }

    @Override
    public void dispose( LuceneConfig instance ) {

    }

}
