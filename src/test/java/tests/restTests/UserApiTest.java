package tests.restTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import rest.base.BaseTest;
import rest.model.UserCreate;
import rest.model.UserResponse;

import static io.restassured.RestAssured.given;
import static rest.specs.ApiSpecs.successSpec;

@Test
public class UserApiTest extends BaseTest {
    public void createUserTest(){
        String userName = "autotest_user_" + System.currentTimeMillis();
        UserCreate request = UserCreate.builder()
                .username(userName)
                .password("Password123!")
                .email(userName + "@test.com")
                .firstName("John")
                .lastName("John")
                .build();

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(request)
                    .when()
                    .post("/users/add")
                    .then()
                    .spec(successSpec)
                    .extract().response();

            UserResponse created = response.as(UserResponse.class);

            Assert.assertNotNull(created.getId());
            Assert.assertEquals(created.getUsername(), request.getUsername());
            Assert.assertEquals(created.getEmail(), request.getEmail());
    }
}
