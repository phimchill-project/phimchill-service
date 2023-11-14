CREATE TABLE IF NOT EXISTS roles
(
    ID          bigint auto_increment
        primary key,
    NAME        varchar(50) null,
    DESCRIPTION varchar(50) null
);

INSERT INTO phim_chill.roles (ID, NAME, DESCRIPTION) VALUES (1, 'ROLE_ADMIN', 'Admin');
INSERT INTO phim_chill.roles (ID, NAME, DESCRIPTION) VALUES (2, 'ROLE_USER', 'Customer');

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