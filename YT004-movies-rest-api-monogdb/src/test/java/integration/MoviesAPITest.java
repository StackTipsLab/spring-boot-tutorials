package integration;

import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

class MoviesAPITest {

    String baseUrl = "http://localhost:8080";

    @Test
    void testGetMoviesList() {
        // Perform the GET request to retrieve the list of movies
        Response response = given().baseUri(baseUrl)
                .when()
                .get("api/1.0/movies")
                .then().
                statusCode(200)
                .contentType("application/json")
                .extract()
                .response();

        response.then().body("$", hasSize(3));
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
                .pathParam("movieId", movieId)
                .when()
                .get("api/1.0/movies/{movieId}")
                .then()
                .statusCode(200)
                .contentType("application/json")
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

//    @Test
//    void testCreateMovie() throws JSONException {
//        JSONObject movieJson = new JSONObject();
//        movieJson.put("name", "Timmy");
//        movieJson.put("passportNumber", "ZZZ12345");
//
//        given()
//                .baseUri(baseUrl)
//
//                .when()
//                .get("api/1.0/movies/{movieId}")
//                .then()
//                .statusCode(404);
//    }

}
