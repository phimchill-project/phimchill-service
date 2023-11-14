<<<<<<< HEAD
CREATE TABLE IF NOT EXISTS roles
(
    ID          bigint auto_increment
        primary key,
    NAME        varchar(50) null,
    DESCRIPTION varchar(50) null
);

CREATE TABLE IF NOT EXISTS users
(
    ID       bigint auto_increment
        primary key,
    EMAIL    varchar(50) not null,
    NAME     varchar(50) not null,
    PASSWORD varchar(50) not null,
    ROLE_ID  bigint      null,
    constraint fk_users_roles
        foreign key (ROLE_ID) references roles (ID)
);

=======
CREATE TABLE USERS
(
    ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
    EMAIL    VARCHAR(50) NOT NULL ,
    NAME VARCHAR(50) NOT NULL ,
    PASSWORD VARCHAR(50) NOT NULL
);
>>>>>>> a3faaae2dc122349f674a11a9e41e7c9dc100cb8
