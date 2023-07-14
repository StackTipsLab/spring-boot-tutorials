package com.stacktips.movies.repository;

import com.stacktips.movies.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieMongoRepository extends MongoRepository<Movie, String> {

}
