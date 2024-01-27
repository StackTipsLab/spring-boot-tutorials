package com.stacktips.movies.repository;

import com.stacktips.movies.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface MovieRepository extends MongoRepository<Movie, String> {

}