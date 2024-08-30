package com.stacktips.movies.api;

import com.stacktips.movies.dto.MovieDto;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/1.0/movies", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MoviesController {

    private final MovieService movieService;

    @GetMapping
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
