package com.stacktips.app.api;

import com.stacktips.app.model.Movie;
import com.stacktips.app.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/primary/movies")
    public List<Movie> getMoviesPrimary() {
        return movieService.findAllPrimary();
    }

    @PostMapping(path = "/primary/movies")
    public Movie saveMoviePrimary(@RequestBody Movie movie) {
        return movieService.savePrimary(movie);
    }

    @GetMapping(path = "/secondary/movies")
    public List<Movie> getMoviesSecondary() {
        return movieService.findAllSecondary();
    }

    @PostMapping(path = "/secondary/movies")
    public Movie saveMovieSecondary(@RequestBody Movie movie) {
        return movieService.saveSecondary(movie);
    }

}
