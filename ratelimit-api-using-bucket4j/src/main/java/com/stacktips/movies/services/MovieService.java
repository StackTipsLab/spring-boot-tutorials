package com.stacktips.movies.services;

import com.stacktips.movies.dto.MovieDto;
import com.stacktips.movies.exception.MovieNotFoundException;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

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

    public Movie updateMovie(String movieId, MovieDto movieDto) {
        if (!repository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie with id '" + movieId + "' not found.");
        }

        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);
        movie.setId(movieId);
        return repository.save(movie);
    }

    public void deleteMovie(String movieId) {
        if (!repository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie with id '" + movieId + "' not found.");
        }
        repository.deleteById(movieId);
    }
}