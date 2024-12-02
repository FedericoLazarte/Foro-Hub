create table users(
    id bigserial not null,
    login varchar(100) not null,
    password varchar(300) not null,
    primary key(id)
);