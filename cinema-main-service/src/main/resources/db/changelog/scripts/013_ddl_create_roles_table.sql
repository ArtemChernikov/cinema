create table if not exists roles
(
    id serial primary key,
    name varchar unique not null
);