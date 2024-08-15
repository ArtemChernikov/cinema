create table if not exists roles
(
    id   serial primary key,
    name varchar unique not null
);

create table if not exists users
(
    id       bigserial primary key,
    name     varchar        not null,
    surname  varchar        not null,
    email    varchar        not null,
    username varchar unique not null,
    password varchar        not null,
    role_id  bigint references roles (id)
);

create table if not exists users_roles
(
    id bigserial primary key,
    user_id bigint references users(id) not null,
    role_id bigint references roles(id) not null,
    unique (user_id, role_id)
);

