CREATE TABLE IF NOT EXISTS CATEGORY
(
    ID   BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS MOVIE
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME        VARCHAR(50)  ,
    DESCRIPTION VARCHAR(1000)  ,
    YEAR        INT,
    DURATION    INT,
    IMDB        FLOAT        ,
    IMAGE       VARCHAR(500) ,
    TRAILER     VARCHAR(100) ,
    URL VARCHAR(500),
    IS_RELEASE BOOLEAN DEFAULT TRUE,
    DATE_RELEASE DATE,
<<<<<<< HEAD
    VIEWS INT,
<<<<<<< HEAD
    IS_DELETE BOOLEAN DEFAULT TRUE

    );
=======
    IS_DELETE BOOLEAN
=======
    VIEWS INT DEFAULT 0,
    IS_DELETE BOOLEAN DEFAULT FALSE
>>>>>>> bcb118dda2b811d0c1ee35aa32c674d123dcee3c
);
>>>>>>> 7c0345280faaa782b7293f626a9e7403c9427974

create table tvseries
(
    ID           bigint auto_increment
        primary key,
    NAME         varchar(50)          null,
    DESCRIPTION  varchar(1000)        null,
    YEAR         int                  null,
    IMDB         float                null,
    IMAGE        varchar(500)         null,
    TRAILER      varchar(100)         null,
    IS_RELEASE   tinyint(1) default 1 null,
    DATE_RELEASE date                 null,
    VIEWS        int                  null,
    IS_DELETE    tinyint(1)           null,
    MoreImage    varchar(5000)        null
);

CREATE TABLE IF NOT EXISTS CATEGORY_MOVIE
(
    CATEGORY_ID BIGINT ,
    MOVIE_ID    BIGINT ,
    PRIMARY KEY (CATEGORY_ID, MOVIE_ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID),
    FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE (ID)
);

CREATE TABLE IF NOT EXISTS CATEGORY_TVSERIES
(
    CATEGORY_ID BIGINT ,
    TVSERIES_ID    BIGINT ,
    PRIMARY KEY (CATEGORY_ID, TVSERIES_ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID),
    FOREIGN KEY (TVSERIES_ID) REFERENCES TVSERIES (ID)
);