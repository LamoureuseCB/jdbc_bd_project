
drop table if exists categories cascade;
drop table if exists products cascade ;
create table if not exists categories
(
    id   serial4 primary key,
    name varchar(255) not null unique
    );

create table if not exists products
(
    id          serial4 primary key,
    name        varchar(255)                    not null,
    price       double precision                not null,
    category_id int references categories (id) not null
    );