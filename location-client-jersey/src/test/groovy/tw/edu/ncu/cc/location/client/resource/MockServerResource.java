package tw.edu.ncu.cc.location.client.resource;

import org.junit.rules.ExternalResource;
import org.mockserver.integration.ClientAndServer;

public class MockServerResource extends ExternalResource {

    public static int port = 8899; //TODO READ FROM EXT

    private ClientAndServer mockServer;

    @Override
    protected void before() throws Throwable {
        mockServer = ClientAndServer.startClientAndServer( port );
    }

    @Override
    protected void after() {
        mockServer.stop();
    }

    public ClientAndServer getMockServer() {
        return mockServer;
    }

}
