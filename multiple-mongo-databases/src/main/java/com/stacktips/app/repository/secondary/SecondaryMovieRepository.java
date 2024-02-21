package com.stacktips.app.repository.secondary;

import com.stacktips.app.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryMovieRepository extends MongoRepository<Movie, String> {
}