package tw.edu.ncu.cc.location.server.resource;

import com.jayway.restassured.RestAssured;
import groovy.json.JsonSlurper;
import org.junit.rules.ExternalResource;

public class HttpResource extends ExternalResource {

    @Override
    protected void before() throws Throwable { //TODO READ FROM EXT
        RestAssured.baseURI  = "http://127.0.0.1";
        RestAssured.basePath = "/location-server";
        RestAssured.port     = 8591;
    }

    @Override
    protected void after() {
        super.after();
    }

    public static Object JSON( String body ) {
        return new JsonSlurper().parseText( body );
    }

    public static Object requestJSON( String path ) {
        return JSON( requestString( path ) );
    }

    public static String requestString( String path ) {
        return RestAssured.get( path ).asString();
    }

}
