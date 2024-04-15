CREATE TABLE customers
(
    subscription_date DATE,
    city              CHARACTER VARYING(255),
    company           CHARACTER VARYING(255),
    country           CHARACTER VARYING(255),
    customer_id       CHARACTER VARYING(255) NOT NULL,
    email             CHARACTER VARYING(255),
    first_name        CHARACTER VARYING(255),
    last_name         CHARACTER VARYING(255),
    phone1            CHARACTER VARYING(255),
    phone2            CHARACTER VARYING(255),
    website           CHARACTER VARYING(255),
    PRIMARY KEY (customer_id)
);
