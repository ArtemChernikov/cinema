create table if not exists ratings
(
    id  bigserial primary key,
    kp_rate decimal not null,
    imdb_rate decimal not null,
    film_critics_rate decimal not null,
    russian_film_critics_rate decimal not null
);

create table if not exists posters
(
    id  bigserial primary key,
    url text not null,
    preview_url text not null
);

create table if not exists backdrops
(
    id  bigserial primary key,
    url text not null,
    preview_url text not null
);

create table if not exists genres
(
    id  bigserial primary key,
    name varchar not null unique
);

create table if not exists countries
(
    id  bigserial primary key,
    name varchar not null unique
);

create table if not exists films
(
    id  bigint primary key,
    name varchar not null,
    alternative_name varchar,
    type varchar not null,
    year varchar not null,
    description text,
    short_description text,
    rating_id bigint references ratings(id) not null,
    movie_length int,
    age_rating int,
    poster_id bigint references posters(id) not null,
    backdrop_id bigint references backdrops(id)
);

create table if not exists films_genres
(
    id  bigserial primary key,
    film_id bigint references films(id) not null,
    genre_id bigint references genres(id) not null,
    unique (film_id, genre_id)
);

create table if not exists films_countries
(
    id  bigserial primary key,
    film_id bigint references films(id) not null,
    country_id bigint references countries(id) not null,
    unique (film_id, country_id)
);