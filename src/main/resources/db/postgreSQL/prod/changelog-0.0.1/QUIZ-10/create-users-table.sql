CREATE TABLE USERS
(
    username varchar(20)  not null,
    password varchar(100) not null,
    role     varchar(5)   not null,
    CONSTRAINT users_pk PRIMARY KEY (username)
)