package selenium.tests.restTests;

import org.testng.annotations.Test;
import rest.base.BaseTestApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthApiTest extends BaseTestApi {
    @Test
    public void getProfileWithValidToken(){
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/me")
                .then()
                .statusCode(200)
                .body("username", equalTo("emilys"))
                .body("email", equalTo("emily.johnson@x.dummyjson.com"))
                .body("id", equalTo(1));
    }
}
