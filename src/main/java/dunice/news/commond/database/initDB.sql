CREATE TABLE IF NOT EXISTS news
(
    id SERIAL PRIMARY KEY NOT NULL,
    images VARCHAR(160) NOT NULL,
    tags VARCHAR(160) NOT NULL ,
    title VARCHAR(160) NOT NULL ,
    created_At VARCHAR(100) NOT NULL
);