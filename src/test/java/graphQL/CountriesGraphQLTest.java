package graphQL;

import graphQL.pojos.CountryVariables;
import graphQL.pojos.GraphQLRequest;
import graphQL.queries.CountryQueries;
import org.testng.annotations.Test;

import static graphQL.queries.CountryQueries.COUNTRY_QUERY;
import static graphQL.specs.GraphQLSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CountriesGraphQLTest {

    @Test
    public void returnListOfCountriesTest(){
        CountryVariables countryVariables = new CountryVariables("PL");
        GraphQLRequest<CountryVariables> request = new GraphQLRequest<>(COUNTRY_QUERY, countryVariables);

        given()
                    .spec(requestSpec())
                    .body(request)
                .when()
                    .post("/")
                .then()
                    .statusCode(200)
                    .body("data.country.code", equalTo("PL"))
                    .body("data.country.name", equalTo("Poland"))
                    .body("data.country.capital", notNullValue());
    }

}
