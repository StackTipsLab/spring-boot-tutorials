package integration;

import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

class MoviesAPITest {

    private final String baseUrl = "http://localhost:8080";

    private Map<String, Object> headers;


    @BeforeEach
    void setUp() {
        headers = Map.of(
                "Content-type", MediaType.APPLICATION_JSON_VALUE,
                "Accept-type", MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void testGetMoviesList() {

        Response response = given().baseUri(baseUrl)
                .headers(headers)
                .when()
                .get("api/1.0/movies")
                .then().
                statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .response();

        response.then().body("[0].id", equalTo("64b152e16a386772c6453c94"));
        response.then().body("[0].title", equalTo("Iron Man & Captain America: Heroes United"));
        response.then().body("[0].actors", containsInAnyOrder("David Kaye", "Ian McKellen", "Adrian Pasdar"));
        response.then().body("[0].genres", containsInAnyOrder("Action", "Adventure", "Sci-fi"));

        response.then().body("[1].id", equalTo("64b15c2e8c24e95ffcbe786f"));
        response.then().body("[1].title", equalTo("Transformers: Rise of the Beasts"));
        response.then().body("[1].actors", containsInAnyOrder("David Kaye", "Ian McKellen", "Adrian Pasdar"));
        response.then().body("[1].genres", containsInAnyOrder("Action", "Adventure", "Sci-fi", "Fantasy"));
    }

    @Test
    void testGetMovieById() {
        String movieId = "64b15c2e8c24e95ffcbe786f";
        // Perform the GET request to retrieve the list of movies
        given()
                .baseUri(baseUrl)
                .headers(headers)
                .pathParam("movieId", movieId)
                .when()
                .get("api/1.0/movies/{movieId}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", equalTo(movieId))
                .body("title", equalTo("Transformers: Rise of the Beasts"))
                .body("actors", hasItems("David Kaye", "Ian McKellen", "Adrian Pasdar"))
                .body("genres", hasItems("Action", "Adventure", "Sci-fi", "Fantasy"))
                .extract()
                .response();
    }

    @Test
    void testGetMovieByIdNotFoundError() {
        String movieId = "INVALID_MOVIE_ID";
        given()
                .baseUri(baseUrl)
                .pathParam("movieId", movieId)
                .when()
                .get("api/1.0/movies/{movieId}")
                .then()
                .statusCode(404);
    }

    @Test
    void testCreateMovie() throws JSONException {
        JSONObject movieJson = new JSONObject();
        movieJson.put("title", "Transformers");
        movieJson.put("genres", "ZZZ12345");

        given()
                .baseUri(baseUrl)
                .headers(headers)
                .when()
                .get("api/1.0/movies")
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(200)
                .body("id", is(notNullValue()))
        ;
    }

}
