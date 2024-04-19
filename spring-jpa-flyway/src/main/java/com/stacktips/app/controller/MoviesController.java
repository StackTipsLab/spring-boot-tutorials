package com.stacktips.app.controller;

import com.stacktips.app.model.Movie;
import com.stacktips.app.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MovieRepository movieRepository;

    @GetMapping()
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

}