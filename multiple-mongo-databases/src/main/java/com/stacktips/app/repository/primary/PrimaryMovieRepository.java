package com.stacktips.app.repository.primary;

import com.stacktips.app.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryMovieRepository extends MongoRepository<Movie, String> {
}
