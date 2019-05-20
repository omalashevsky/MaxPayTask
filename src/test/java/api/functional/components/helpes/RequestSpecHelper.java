package api.functional.components.helpes;

import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;

public class RequestSpecHelper
{
    private static final String BASE_URI = "https://swapi.co/api/";

    public static RequestSpecification baseRequestSpec()
    {
        return given()
                .baseUri(BASE_URI);
    }
}