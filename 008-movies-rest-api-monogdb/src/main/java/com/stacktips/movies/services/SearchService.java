package com.stacktips.movies.services;

import com.stacktips.movies.dto.SearchRequest;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.repository.MovieSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final MovieSearchRepository searchRepository;

    public SearchService(MovieSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<Movie> searchMovieByLanguage(String language) {
        return searchRepository.searchMovieByLanguage(language);
    }

    public List<Movie> search(SearchRequest searchRequest) {
        return searchRepository.search(searchRequest);
    }

}