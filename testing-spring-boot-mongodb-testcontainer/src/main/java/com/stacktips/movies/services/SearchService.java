package com.stacktips.movies.services;

import com.stacktips.movies.dto.SearchRequest;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final MovieRepository searchRepository;

    public SearchService(MovieRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<Movie> search(SearchRequest searchRequest) {
        return searchRepository.searchMovies(searchRequest);
    }

}