CREATE TABLE movie(
    id INT PRIMARY KEY,
    title VARCHAR(1000),
    overview VARCHAR(1000),
    poster_path VARCHAR(255),
    release_date VARCHAR(50),
    vote_count INT,
    vote_average FLOAT
);

CREATE TABLE visited(
    movie_id INT PRIMARY KEY,
    visit_date DATETIME,
    FOREIGN KEY(movie_id) REFERENCES movie(id)
);