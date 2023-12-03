CREATE TABLE IF NOT EXISTS ROLES
(
    ID          bigint auto_increment primary key,
    NAME        varchar(50) not null,
    DESCRIPTION varchar(50) not null
);

CREATE TABLE IF NOT EXISTS USERS
(
    ID       bigint auto_increment primary key,
    EMAIL    varchar(50),
    NAME     varchar(50),
    PASSWORD varchar(500),
    ROLE_ID  bigint not null,
    FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID)
);

INSERT INTO ROLES (ID, NAME, DESCRIPTION)
VALUES (1, 'ROLE_ADMIN', 'Admin');
INSERT INTO ROLES (ID, NAME, DESCRIPTION)
VALUES (2, 'ROLE_USER', 'Customer');