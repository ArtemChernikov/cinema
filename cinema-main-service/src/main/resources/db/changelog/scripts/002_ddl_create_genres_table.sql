create table if not exists genres
(
    id   bigserial primary key,
    name varchar unique not null
);