create table USERS
(
    id       bigint generated by default as identity,
    user_id  varchar(255) unique,
    name     varchar(255),
    password varchar(255),
    regNo    varchar(255),
    primary key (id)
);