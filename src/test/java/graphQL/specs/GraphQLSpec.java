package graphQL.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class GraphQLSpec {
    public static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://countries.trevorblades.com/")
                .setContentType("application/json")
                .log(ALL)
                .build();
    }
}
