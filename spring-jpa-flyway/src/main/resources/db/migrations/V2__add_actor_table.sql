CREATE TABLE actor
(
    id         bigint NOT NULL AUTO_INCREMENT,
    first_name varchar(255) DEFAULT NULL,
    last_name  varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;