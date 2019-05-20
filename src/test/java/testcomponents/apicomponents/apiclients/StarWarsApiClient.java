package testcomponents.apicomponents.apiclients;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;


public class StarWarsApiClient
{
    public static Response performGetRequest(final String resource, final int expectedStatusCode)
    {
        return given()
                .expect()
                .statusCode(expectedStatusCode)
                .when()
                .get(resource)
                .then()
                .log().all()
                .extract().response();
    }
}
