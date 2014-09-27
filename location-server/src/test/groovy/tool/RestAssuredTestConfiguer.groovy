package tool

import com.jayway.restassured.RestAssured;

class RestAssuredTestConfiguer {

    public static void configure() { //TODO READ FROM SYSPROP
        RestAssured.baseURI  = "http://127.0.0.1"
        RestAssured.basePath = "/location-server"
        RestAssured.port     = 8591
    }

}
