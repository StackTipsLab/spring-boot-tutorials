package com.stacktips.movies.repository;

import com.stacktips.movies.dto.SearchRequest;
import com.stacktips.movies.models.Movie;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieSearchRepository {

    private final MongoTemplate mongoTemplate;

    public MovieSearchRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Movie> searchMovieByLanguage(String language) {
        Query query = new Query(Criteria.where("language").is(language));
        return mongoTemplate.find(query, Movie.class);
    }

    public List<Movie> search(SearchRequest searchRequest) {
        Query query = new Query();

        if (null != searchRequest.getRating()) {
            query.addCriteria(Criteria.where("rating").is(searchRequest.getRating()));
        }

        if (null != searchRequest.getLanguage()) {
            query.addCriteria(Criteria.where("language").is(searchRequest.getLanguage()));
        }

        if (null != searchRequest.getGenres() && searchRequest.getGenres().length > 0) {
            query.addCriteria(Criteria.where("genres").in(searchRequest.getGenres()));
        }

        if (null != searchRequest.getActors() && searchRequest.getActors().length > 0) {
            query.addCriteria(Criteria.where("actors").in(searchRequest.getActors()));
        }

        return mongoTemplate.find(query, Movie.class);
    }
}
