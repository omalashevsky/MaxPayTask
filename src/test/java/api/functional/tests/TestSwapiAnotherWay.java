package api.functional.tests;

import com.jayway.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import api.functional.components.apiclients.StarWarsApiClientAnotherWayToImplement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSwapiAnotherWay
{
    private int testHeroId = 1;
    private int testHeroPlanetId;

    @Test(description = "this test case checks that Luke Skywalker id = 1")
    public void verifyNameOfTheTestHero()
    {
        String testHeroExpectedName = "Luke Skywalker";

        Response response = StarWarsApiClientAnotherWayToImplement.getHumanById(testHeroId);
        assertThat(response.jsonPath().getString("name"))
                .isEqualTo(testHeroExpectedName);
    }

    @Test(description = "this test case verifies planet's attributes with testHeroId")
    public void verifyLukeSkywalkerPlanet()
    {
        String expectedName = "Tatooine";
        String expectedPopulation = "200000";

        //Another way is to create a response json class HumanResp.class, then rest-assured response.as(HumanResp.class).getHomeWorld();
        String homeworld = StarWarsApiClientAnotherWayToImplement.getHumanById(testHeroId)
                .jsonPath()
                .getString("homeworld");
        //Extracting id of the homeworld string value. Of course, we can just use the homeworld link for GET request. but most probably homeworld id will be returned in the response in the real API
        testHeroPlanetId = getIdFromTheResourceLink(homeworld);
        JSONObject lukePlanet = new JSONObject(StarWarsApiClientAnotherWayToImplement.getPlanetById(testHeroPlanetId).body().asString());

        //yes, we can use rest-assured asserts, but I prefer using assertJ library, it has a lot of benefits. Rest-assured is used for API clients
        assertThat(lukePlanet.getString("name"))
                .isEqualTo(expectedName);
        assertThat(lukePlanet.getString("population"))
                .isEqualTo(expectedPopulation);
    }

    //this test depends on verifyLukeSkywalkerPlanet because if Luke's planet doesn't exist - then the planet's films can't be verified
    //THE TEST FAILS!!!! the first film's id = 5, this film doesn't contain human with id = 1 which belongs to Luke
    @Test(dependsOnMethods = {"verifyLukeSkywalkerPlanet"}, description = "this test case verifies Luke's planet first film")
    public void verifyLukePlanetFilms()
    {
        String expectedTitle = "Attack of the Clones";
        String firstFilm = StarWarsApiClientAnotherWayToImplement.getPlanetById(testHeroPlanetId)
                .jsonPath()
                .getList("films")
                .get(0)
                .toString();

        int filmId = getIdFromTheResourceLink(firstFilm);
        Response actualResult = StarWarsApiClientAnotherWayToImplement.getFilmById(filmId);
        //verify the film's title
        assertThat(actualResult.jsonPath().getString("title"))
                .isEqualTo(expectedTitle);
        //verify if the film contains Luke's planet
        List<Integer> planetIds = actualResult
                .jsonPath()
                .getList("planets")
                .stream()
                .map( planet -> getIdFromTheResourceLink(planet.toString()))
                .collect(Collectors.toList());
        assertThat(planetIds)
                .contains(testHeroPlanetId);
        //verify if the film contains Luke Skywalker. NOTE!!! the test is failing ... the first film's id = 5, this film doesn't contain human with id = 1
        List<Integer> humanIds = actualResult
                .jsonPath()
                .getList("characters")
                .stream()
                .map( humanResource -> getIdFromTheResourceLink(humanResource.toString()))
                .collect(Collectors.toList());
        assertThat(humanIds)
                .contains(testHeroId);
    }

    private int getIdFromTheResourceLink(String dataToScan)
    {
        return getIdFromTheResourceLink("(/\\d+/)$", dataToScan);
    }

    private int getIdFromTheResourceLink(String pattern, String dataToScan) {
        Integer id = null;

        Matcher m = Pattern.compile(pattern).matcher(dataToScan);
        while (m.find()) {
            id = Integer.parseInt(m.group().replaceAll("/", ""));
        }

        if (id == null)
            throw new RuntimeException("TestSwapiAnotherWay.getIdFromTheResourceLink(): id wasn't found in [" + dataToScan + "] resource by pattern [" + pattern + "]");

        return id.intValue();
    }
}
