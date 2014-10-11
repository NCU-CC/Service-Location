package tw.edu.ncu.cc.location.client.resource;

import org.junit.rules.ExternalResource;
import tw.edu.ncu.cc.location.client.jersey.NCUSynLocationClient;
import tw.edu.ncu.cc.location.client.tool.config.LocationConfig;
import tw.edu.ncu.cc.location.client.tool.config.NCULocationConfig;

public class LocationClientResource extends ExternalResource {

    private NCUSynLocationClient client;


    @Override
    protected void before() throws Throwable {
        LocationConfig config = new NCULocationConfig();
        config.setServerAddress( "http://127.0.0.1:" + MockServerResource.port );
        client = new NCUSynLocationClient( config );
    }

    @Override
    protected void after() {

    }

    public NCUSynLocationClient getClient() {
        return client;
    }

}
