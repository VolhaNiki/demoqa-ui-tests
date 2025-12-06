package rest.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import rest.AuthApi;

public class BaseTestApi {
    protected String token;
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        token = new AuthApi().loginAndGetToken();
    }
}
