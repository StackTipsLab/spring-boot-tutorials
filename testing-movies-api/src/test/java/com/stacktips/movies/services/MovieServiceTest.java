package com.stacktips.movies.services;

import com.stacktips.movies.dto.MovieDto;
import com.stacktips.movies.models.ContentRating;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.stacktips.movies.models.ContentRating.PG;
import static com.stacktips.movies.models.ContentRating.PG13;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataMongoTest
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;


    @Test
    void getMovies_ReturnsListOfMovies() {
        List<Movie> movies = Arrays.asList(
                mockMovie("64b152e16a386772c6453c94", "Iron Man", "EN", List.of("David Kaye", "Ian McKellen"),
                        PG),
                mockMovie("64b15c378c24e95ffcbe7870", "Mission Impossible", "FR", List.of("Tom Cruise"),
                        PG13)
        );
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getMovies();

        assertEquals(2, result.size());
        verify(movieRepository).findAll();
    }

    @Test
    void getMovie_WithValidId_ReturnsMovie() {
        String movieId = "movie_id";
        Movie movie = mockMovie(movieId, "Iron Man", "EN", List.of("David Kaye", "Ian McKellen"),
                PG);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovie(movieId);

        assertNotNull(result);
        verify(movieRepository).findById(movieId);
    }

    @Test
    void createMovie_SavesAndReturnsMovie() {
        MovieDto movieDto = mockMovieDto("Iron Man", "EN", List.of("David Kaye", "Ian McKellen"), PG13);
        Movie movie = mockMovie("test_id", "Iron Man", "EN", List.of("David Kaye", "Ian McKellen"),
                PG13);

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        Movie result = movieService.createMovie(movieDto);

        assertNotNull(result);
        verify(movieRepository).save(any(Movie.class));
        assertThat(result, is(notNullValue()));
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
