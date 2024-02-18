package com.stacktips.movies.api;

import com.stacktips.movies.dto.SearchRequest;
import com.stacktips.movies.models.Movie;
import com.stacktips.movies.services.SearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Search")
@RestController
@RequestMapping(value = "/api/1.0/search", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(path = "/language")
    public List<Movie> searchByLanguage(@RequestParam(name = "language") String language) {
        return searchService.searchMovieByLanguage(language);
    }

    @GetMapping
    public List<Movie> search(SearchRequest searchRequest) {
        return searchService.search(searchRequest);
    }

}
