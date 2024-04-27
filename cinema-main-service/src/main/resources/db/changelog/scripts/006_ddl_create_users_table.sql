create table if not exists users
(
    id       serial primary key,
    name     varchar        not null,
    surname  varchar        not null,
    email    varchar        not null,
    username varchar unique not null,
    password varchar        not null
);