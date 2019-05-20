package testcomponents.apicomponents.apiclients;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import static org.apache.http.HttpStatus.SC_OK;
import static testcomponents.apicomponents.helpers.RequestSpecHelper.baseRequestSpec;

//OTHER WAY TO IMPLEMENT API TESTING, AS FOR ME THIS WAY IS MORE HUMAN READABLE WHEN USING IN TEST CLASSES. TestSwapiAnotherWay uses this API
public class StarWarsApiClientAnotherWayToImplement
{
    //for success case
    public static Response getPlanetById(final int planetId)
    {
        return getPlanetById(baseRequestSpec(), planetId, SC_OK);
    }

    //can be used for verifying error cases and for checking with user specific requestSpecification
    public static Response getPlanetById(final RequestSpecification requestSpecification, final int planetId, final int expectedStatusCode)
    {
        return requestSpecification
                .expect()
                .statusCode(expectedStatusCode)
                .when()
                .get("/planets/{id}", planetId)
                .then()
                .log().all()
                .extract().response();
    }

    //for success case
    public static Response getHumanById(final int manId)
    {
        return getHumanById(baseRequestSpec(), manId, SC_OK);
    }

    //can be used for verifying error cases and for checking with user specific requestSpecification
    public static Response getHumanById(final RequestSpecification requestSpecification, final int manId, final int expectedStatusCode)
    {
        return requestSpecification
                .expect()
                .statusCode(expectedStatusCode)
                .when()
                .get("/people/{id}", manId)
                .then()
                .log().all()
                .extract().response();
    }

    //for success case
    public static Response getFilmById(final int filmId)
    {
        return getFilmById(baseRequestSpec(), filmId, SC_OK);
    }

    //can be used for verifying error cases and for checking with user specific requestSpecification
    public static Response getFilmById(final RequestSpecification requestSpecification, final int filmId, final int expectedStatusCode)
    {
        return requestSpecification
                .expect()
                .statusCode(expectedStatusCode)
                .when()
                .get("/films/{id}", filmId)
                .then()
                .log().all()
                .extract().response();
    }
}
