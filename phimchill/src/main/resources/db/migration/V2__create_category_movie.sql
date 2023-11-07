CREATE TABLE CATEGORY
(
    ID   BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(50)
);

CREATE TABLE MOVIE
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME        VARCHAR(50)  NOT NULL,
    DESCRIPTION VARCHAR(50)  NOT NULL,
    YEAR        DATE,
    DURATION    INT,
    IMDB        FLOAT        NOT NULL,
    IMAGE       VARCHAR(100) NOT NULL,
    TRAILER     VARCHAR(100) NOT NULL
);

CREATE TABLE CATEGORY_MOVIE
(
    CATEGORY_ID BIGINT NOT NULL,
    MOVIE_ID    BIGINT NOT NULL,
    PRIMARY KEY (CATEGORY_ID, MOVIE_ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID),
    FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE (ID)
);