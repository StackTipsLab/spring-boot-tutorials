INSERT INTO movie (title, headline, language, region, thumbnail, rating)
VALUES ('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology.', 'English',
        'USA', 'inception.jpg', 'PG13'),
       ('The Godfather',
        'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
        'English', 'USA', 'godfather.jpg', 'R'),
       ('Parasite',
        'A poor family, the Kims, con their way into becoming the servants of a rich family, the Parks. But their easy life gets complicated when their deception is threatened with exposure.',
        'Korean', 'South Korea', 'parasite.jpg', 'R'),
       ('Amélie',
        'Amélie is an innocent and naive girl in Paris with her own sense of justice. She decides to help those around her and, along the way, discovers love.',
        'French', 'France', 'amelie.jpg', 'R');

-- Inserting data into the 'actor' table
INSERT INTO actor (first_name, last_name)
VALUES ('Leonardo', 'DiCaprio'),
       ('Al', 'Pacino'),
       ('Song', 'Kang-ho'),
       ('Audrey', 'Tautou');

-- Leonardo DiCaprio in Inception
INSERT INTO movie_actors (actors_id, movie_id)
VALUES (1, 1);

-- Al Pacino in The Godfather
INSERT INTO movie_actors (actors_id, movie_id)
VALUES (2, 2);

-- Song Kang-ho in Parasite
INSERT INTO movie_actors (actors_id, movie_id)
VALUES (3, 3);

-- Audrey Tautou in Amélie
INSERT INTO movie_actors (actors_id, movie_id)
VALUES (4, 4);