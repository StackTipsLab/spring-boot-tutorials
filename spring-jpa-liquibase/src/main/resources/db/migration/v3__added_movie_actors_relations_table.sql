CREATE TABLE movie_actors
(
    actors_id bigint NOT NULL,
    movie_id  bigint NOT NULL,
    PRIMARY KEY (actors_id, movie_id),
    KEY       fk_ref_movie (movie_id),
    CONSTRAINT fk_ref_movie FOREIGN KEY (movie_id) REFERENCES movie (id),
    CONSTRAINT fl_ref_actor FOREIGN KEY (actors_id) REFERENCES actor (id)
) ENGINE=InnoDB;