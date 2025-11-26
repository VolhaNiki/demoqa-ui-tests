package rest.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class ApiSpecs {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification successSpec = new ResponseSpecBuilder()
            .expectStatusCode(anyOf(is(200), is(201)))
            .build();
}
