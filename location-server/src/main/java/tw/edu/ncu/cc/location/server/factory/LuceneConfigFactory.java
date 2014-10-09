package tw.edu.ncu.cc.location.server.factory;

import org.glassfish.hk2.api.Factory;
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LuceneConfigFactory implements Factory<LuceneConfig> {

    @Override
    public LuceneConfig provide() {
        return new LuceneConfig( readConfigFile().getProperty( "lucene.index.path" ) );
    }

    private Properties readConfigFile() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try( InputStream inputStream = classloader.getResourceAsStream( "lucene.properties" ) ){

            Properties  properties  = new Properties();
            properties.load( inputStream );
            return properties;

        } catch ( IOException e ) {
            throw new RuntimeException( "cannot read lucene config file", e );
        }
    }

    @Override
    public void dispose( LuceneConfig instance ) {

    }

}
