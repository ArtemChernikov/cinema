create table if not exists film_sessions
(
    id         bigserial primary key,
    film_id    bigint not null,
    hall_id    int references halls (id),
    start_time timestamp,
    end_time   timestamp,
    price      int
);