DROP TABLE IF EXISTS last_visited;
CREATE TABLE last_visited(
    id INT PRIMARY KEY,
    overview VARCHAR(1000),
    poster_path VARCHAR(255),
    release_date VARCHAR(50),
    vote_average FLOAT
);
