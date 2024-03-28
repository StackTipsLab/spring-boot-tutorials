create table user (
    is_active bit not null,
    id bigint not null auto_increment,
    about_me varchar(255),
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    location varchar(255),
    password varchar(255),
    phone varchar(255),
    user_role enum ('ADMIN','MENTOR','STUDENT'),
    primary key (id)
) engine=InnoDB;
