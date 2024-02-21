package com.stacktips.app.service;

import com.stacktips.app.model.Movie;
import com.stacktips.app.repository.primary.PrimaryMovieRepository;
import com.stacktips.app.repository.secondary.SecondaryMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final PrimaryMovieRepository primaryMovieRepository;
    private final SecondaryMovieRepository secondaryMovieRepository;

    public MovieService(
            PrimaryMovieRepository primaryMovieRepository,
            SecondaryMovieRepository secondaryMovieRepository) {
        this.primaryMovieRepository = primaryMovieRepository;
        this.secondaryMovieRepository = secondaryMovieRepository;
    }

    public Movie savePrimary(Movie movie) {
        return primaryMovieRepository.save(movie);
    }

    public List<Movie> findAllPrimary() {
        return primaryMovieRepository.findAll();
    }

    public Movie saveSecondary(Movie movie) {
        return secondaryMovieRepository.save(movie);
    }

    public List<Movie> findAllSecondary() {
        return secondaryMovieRepository.findAll();
    }
}
