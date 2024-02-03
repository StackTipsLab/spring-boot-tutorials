package com.stacktips.movies.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stacktips.movies.dto.MovieDto;
import com.stacktips.movies.exception.MovieNotFoundException;
import com.stacktips.movies.models.ContentRating;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.stacktips.movies.models.ContentRating.PG;
import static com.stacktips.movies.models.ContentRating.PG13;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MoviesController.class)
class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private ObjectMapper objectMapper;

    private List<Movie> movies;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        movies = Arrays.asList(
                mockMovie("64b152e16a386772c6453c94", "Iron Man", "EN", List.of("David Kaye", "Ian McKellen"),
                        PG),
                mockMovie("64b15c378c24e95ffcbe7870", "Mission Impossible", "FR", List.of("Tom Cruise"),
                        PG13)
        );
    }


    @Test
    void testGetMovies() throws Exception {
        when(movieService.getMovies()).thenReturn(movies);

        mockMvc.perform(get("/api/1.0/movies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is("64b152e16a386772c6453c94")))
                .andExpect(jsonPath("$[0].title", is("Iron Man")))
                .andExpect(jsonPath("$[0].language", is("EN")))
                .andExpect(jsonPath("$[0].actors", containsInAnyOrder("Ian McKellen", "David Kaye")));
        verify(movieService).getMovies();
    }


    @Test
    void testGetMoviesWhenXmlContentType() throws Exception {
        when(movieService.getMovies()).thenReturn(movies);

        mockMvc.perform(get("/api/1.0/movies").contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType());
    }


    @Test
    void testGetMovie() throws Exception {
        String movieId = "test_id";

        Movie movie = mockMovie(movieId, "Iron Man", "EN", List.of("David Kaye", "Ian McKellen"), PG13);
        when(movieService.getMovie(movieId)).thenReturn(movie);

        mockMvc.perform(get("/api/1.0/movies/{movieId}", movieId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.title", is("Iron Man")))
                .andExpect(jsonPath("$.language", is("EN")))
                .andExpect(jsonPath("$.actors", containsInAnyOrder("Ian McKellen", "David Kaye")));

        verify(movieService).getMovie(movieId);
    }


    @Test
    void testGetMovieWhenMovieNotFound() throws Exception {
        String movieId = "test_id";
        when(movieService.getMovie(movieId)).thenThrow(new MovieNotFoundException("Movie not found"));

        mockMvc.perform(get("/api/1.0/movies/{movieId}", movieId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].code", is("NOT_FOUND")))
                .andExpect(jsonPath("$[0].message", is("Movie not found")));

        verify(movieService).getMovie(movieId);
    }


    @Test
    void createMovieTest() throws Exception {
        MovieDto movieDto = mockMovieDto("Iron Man", "EN", List.of("David Kaye", "Ian McKellen"), PG13);
        Movie movie = mockMovie("test_id", "Iron Man", "EN", List.of("David Kaye", "Ian McKellen"),
                PG13);
        when(movieService.createMovie(any(MovieDto.class))).thenReturn(movie);

        mockMvc.perform(post("/api/1.0/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.title", is("Iron Man")))
                .andExpect(jsonPath("$.language", is("EN")))
                .andExpect(jsonPath("$.actors", containsInAnyOrder("Ian McKellen", "David Kaye")));

        verify(movieService).createMovie(any(MovieDto.class));
    }

    private Movie mockMovie(String id, String title, String language, List<String> actors, ContentRating rating) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setHeadline("");
        movie.setThumbnail("");
        movie.setLanguage(language);
        movie.setRegion("");
        movie.setActors(actors);
        movie.setGenres(List.of("Action", "Comedy"));
        movie.setRating(rating);
        return movie;
    }

    private MovieDto mockMovieDto(String title, String language, List<String> actors, ContentRating rating) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(title);
        movieDto.setLanguage(language);
        movieDto.setActors(actors);
        movieDto.setGenres(List.of("Action", "Comedy"));
        movieDto.setRating(rating);
        return movieDto;
    }
}
