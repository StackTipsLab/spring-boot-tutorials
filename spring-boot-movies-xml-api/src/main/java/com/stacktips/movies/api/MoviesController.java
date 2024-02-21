package com.stacktips.movies.api;

import com.stacktips.movies.dto.MovieDto;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.models.Movies;
import com.stacktips.movies.services.MovieService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/1.0/movies",
        consumes = {MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_XML_VALUE}
)
public class MoviesController {

    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Movies> getMovies() {
        Movies movies = new Movies(movieService.getMovies());
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public Movie createMovie(@RequestBody MovieDto movieDto) {
        return movieService.createMovie(movieDto);
    }

    @GetMapping(path = "/{movieId}")
    public Movie getMovie(@PathVariable String movieId) {
        return movieService.getMovie(movieId);
    }

}
