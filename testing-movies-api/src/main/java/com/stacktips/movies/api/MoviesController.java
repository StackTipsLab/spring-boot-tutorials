package com.stacktips.movies.api;

import com.stacktips.movies.dto.MovieDto;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.services.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Movies")
@RestController
@RequestMapping(value = "/api/1.0/movies", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MoviesController {

    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping
    public Movie createMovie(@RequestBody MovieDto movieDto) {
        return movieService.createMovie(movieDto);
    }

    @GetMapping(path = "/{movieId}")
    public Movie getMovie(@PathVariable String movieId) {
        return movieService.getMovie(movieId);
    }

    @PatchMapping(path = "/{movieId}")
    public Movie getMovie(@PathVariable String movieId, @RequestBody MovieDto movieDto) {
        return movieService.updateMovie(movieId, movieDto);
    }

    @DeleteMapping(path = "/{movieId}")
    public ResponseEntity<Object> deleteMovie(@PathVariable String movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok().build();
    }
}
