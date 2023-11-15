CREATE TABLE IF NOT EXISTS ROLES
(
    ID          bigint auto_increment primary key,
    NAME        varchar(50) null,
    DESCRIPTION varchar(50) null
);

CREATE TABLE IF NOT EXISTS USERS
(
    ID       bigint auto_increment primary key,
    EMAIL    varchar(50) not null,
    NAME     varchar(50) not null,
    PASSWORD varchar(50) not null,
    ROLE_ID  bigint      null,
    FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID)
);

INSERT INTO ROLES (ID, NAME, DESCRIPTION)
VALUES (1, 'ROLE_ADMIN', 'Admin');
INSERT INTO ROLES (ID, NAME, DESCRIPTION)
VALUES (2, 'ROLE_USER', 'Customer');