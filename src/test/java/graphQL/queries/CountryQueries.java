package graphQL.queries;

public class CountryQueries {
    public static final String GET_COUNTRY_BY_CODE = """
        query GetCountry($code: ID!) {
          country(code: $code) {
            code
            name
            capital
            currency
          }
        }
        """;

    public static final String COUNTRY_QUERY = """
    query GetCountry($code: ID!) {
      country(code: $code) {
        code
        name
        capital
        currency
      }
    }
    """;
}
