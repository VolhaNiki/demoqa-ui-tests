package tests.restTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import rest.base.BaseTestApi;
import rest.model.product.ProductRequest;
import rest.model.product.ProductResponse;
import rest.specs.ApiSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Test
public class ProductApiTest extends BaseTestApi {
    public void getProductTest() {
        given().log().all()
                .when()
                .get("/products")
                .then()
                .log().all()
                .statusCode(200)
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0))
                .body("products[0].id", notNullValue());
    }

    @Test
    public void extractProductTest() {
        Response response = given()
                .log().all()
                .when()
                .get("/products/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String title = response.jsonPath().getString("title");
        double price = response.jsonPath().getDouble("price");

        Assert.assertEquals(title, "Essence Mascara Lash Princess");
        Assert.assertEquals(price, 9.99);
    }

    @Test
    public void createProductTest() {
        ProductRequest request = ProductRequest.builder()
                .title("Test Product")
                .price(999)
                .build();

        Integer createdID = null;
        try {
            Response response = given()
                        .spec(ApiSpecs.requestSpec)
                        .body(request)
                        .log().all()
                    .when()
                        .post("/products/add")
                    .then()
                        .log().all()
                        .spec(ApiSpecs.success201Spec)
                        .extract().response();

            ProductResponse created = response.as(ProductResponse.class);

            Assert.assertNotNull(created.getId());
            Assert.assertEquals(created.getTitle(), request.getTitle());
            Assert.assertEquals(created.getPrice(), request.getPrice());

            createdID = created.getId();
        } finally {
            if (createdID != null) {
                Response del = given()
                        .spec(ApiSpecs.requestSpec)
                        .when()
                        .log().all()
                        .delete("/products/" + createdID)
                        .andReturn();

                int statusCode = del.getStatusCode();
                if (!(statusCode == 200 || statusCode == 404)){
                    Assert.fail("Unexpected delete status: " + statusCode);
                }
            }
        }
    }
}
