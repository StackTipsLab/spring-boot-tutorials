package com.stacktips.movies.repository;

import com.stacktips.movies.dto.SearchRequest;
import com.stacktips.movies.models.Movie;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    private final MongoTemplate mongoTemplate;

    public MovieRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Movie> searchMovies(SearchRequest searchRequest) {
        Query query = new Query();
        if (null != searchRequest.rating()) {
            query.addCriteria(Criteria.where("rating").is(searchRequest.rating()));
        }

        if (null != searchRequest.language()) {
            query.addCriteria(Criteria.where("language").is(searchRequest.language()));
        }

        if (null != searchRequest.genre()) {
            query.addCriteria(Criteria.where("genres").is(searchRequest.genre()));
        }

        return mongoTemplate.find(query, Movie.class);
    }
}
