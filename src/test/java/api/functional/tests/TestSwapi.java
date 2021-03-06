package api.functional.tests;

import api.functional.components.helpes.RequestSpecHelper;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static api.functional.components.apiclients.StarWarsApiClient.performGetRequest;

public class TestSwapi
{
    private String getHeroByIdRequestLink = RequestSpecHelper.BASE_URI.concat("/people/%d/");
    private int testHeroId = 1;
    private String getTestHeroPlanetRequestLink;

    @Test(description = "this test case checks that Luke Skywalker id = 1")
    public void verifyNameOfTheTestHero()
    {
        String testHeroExpectedName = "Luke Skywalker";

        Response response = performGetRequest(String.format(getHeroByIdRequestLink, testHeroId), SC_OK);
        assertThat(response.jsonPath().getString("name"))
                .isEqualTo(testHeroExpectedName);
    }

    @Test(description = "this test case verifies planet's attributes with testHeroId")
    public void verifyLukeSkywalkerPlanet()
    {
        String expectedName = "Tatooine";
        String expectedPopulation = "200000";

        //Another way is to create a response json class HumanResp.class, then rest-assured response.as(HumanResp.class).getHomeWorld(),
        //thus it will be more convenient to extract/assert data
        getTestHeroPlanetRequestLink = performGetRequest(String.format(getHeroByIdRequestLink, testHeroId), SC_OK)
                .jsonPath()
                .getString("homeworld");

        JSONObject lukePlanet = new JSONObject(performGetRequest(getTestHeroPlanetRequestLink, SC_OK)
                .body()
                .asString());

        //yes, we can use rest-assured asserts, but I prefer using assertJ library, it has a lot of benefits. Rest-assured is used for API clients
        assertThat(lukePlanet.getString("name"))
                .isEqualTo(expectedName);
        assertThat(lukePlanet.getString("population"))
                .isEqualTo(expectedPopulation);
    }

    //this test depends on verifyLukeSkywalkerPlanet because if Luke's planet doesn't exist - then it doesn't make sense to verify films of a non-existing planet
    //THE TEST FAILS!!!! the first film's id = 5, this film doesn't contain human with id = 1 which belongs to Luke
    @Test(dependsOnMethods = {"verifyLukeSkywalkerPlanet"}, description = "this test case verifies Luke's planet first film")
    public void verifyLukePlanetFilms()
    {
        String expectedTitle = "Attack of the Clones";
        String firstFilmResource = performGetRequest(getTestHeroPlanetRequestLink, SC_OK)
                .jsonPath()
                .getList("films")
                .get(0)
                .toString();

        Response actualResult = performGetRequest(firstFilmResource, SC_OK);
        //verify the film's title
        assertThat(actualResult.jsonPath().getString("title"))
                .isEqualTo(expectedTitle);
        //verify if the film contains Luke's planet
        assertThat(actualResult.jsonPath().getList("planets"))
                .contains(getTestHeroPlanetRequestLink);
        //FAILING!!!!
        assertThat(actualResult.jsonPath().getList("characters"))
                .contains(String.format(getHeroByIdRequestLink, testHeroId));
    }
}
