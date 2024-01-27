package com.stacktips.movies.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    public List<String> getMovies() {
        return List.of("The Incredibles", "Father of the Bride", "The Parent Trap");
    }
}
