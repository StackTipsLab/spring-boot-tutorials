create table user_profile (
    id bigint not null auto_increment,
    post_count bigint,
    user_id bigint,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone varchar(255),
    username varchar(255),
    website varchar(255),
    primary key (id)
) engine=InnoDB;

alter table user_profile
   add constraint UK_ebc21hy5j7scdvcjt0jy6xxrv unique (user_id);

alter table user_profile
   add constraint FK6kwj5lk78pnhwor4pgosvb51r
   foreign key (user_id)
   references user (id);
