package rest;

import io.restassured.response.Response;
import rest.specs.ApiSpecs;

import static io.restassured.RestAssured.given;

public class AuthApi {
    public String loginAndGetToken(){
        String loginBody = """
                {
                    "username": "emilys",
                    "password": "emilyspass",
                    "expiresInMins": 30
                }
                """;
        Response response = given()
                .spec(ApiSpecs.requestSpec)
                .body(loginBody)
                .log().all()
                .when()
                .post("/auth/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String token = response.jsonPath().getString("accessToken");

        if (token == null || token.isEmpty()) {
            throw new IllegalStateException(
                    "accessToken пустой. statusCode=" + response.getStatusCode()
                            + ", body=" + response.asString()
            );
        }
        return token;
    }
}
