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

create table if not exists back_drops
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
    alternative_name varchar not null,
    type varchar not null,
    year integer not null,
    description text not null,
    short_description text not null,
    rating_id bigint references ratings(id) not null,
    movie_length int not null check (movie_length > 0),
    age_rating int not null check (age_rating >= 0 and age_rating <= 100),
    poster_id bigint references posters(id) not null
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