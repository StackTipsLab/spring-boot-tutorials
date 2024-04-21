CREATE TABLE movie
(
    id        bigint NOT NULL AUTO_INCREMENT,
    title     varchar(255) DEFAULT NULL,
    headline  varchar(255) DEFAULT NULL,
    language  varchar(255) DEFAULT NULL,
    region    varchar(255) DEFAULT NULL,
    thumbnail varchar(255) DEFAULT NULL,
    rating    enum('G','PG','PG13','R','NC17') DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;