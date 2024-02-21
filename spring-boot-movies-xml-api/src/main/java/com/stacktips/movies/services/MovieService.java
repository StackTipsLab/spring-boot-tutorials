package com.stacktips.movies.services;

import com.stacktips.movies.dto.MovieDto;
import com.stacktips.movies.exception.MovieNotFoundException;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getMovies() {
        return repository.findAll();
    }

    public Movie createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);
        return repository.save(movie);
    }

    public Movie getMovie(String movieId) {
        return repository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id '" + movieId + "' not found."));
    }
}