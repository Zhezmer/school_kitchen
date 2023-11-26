drop table if exists kitchen_order_item, kitchen_order, school_group, product, users, role cascade;

create table if not exists product
(
    id      serial primary key,
    name    varchar(25) not null
);

create table if not exists school_group
(
    id   serial
        primary key,
    name varchar(25) not null
        unique
);

create table if not exists kitchen_order
(
    id            serial  primary key,
    group_id      integer    not null
        constraint fk_group
            references school_group,
    creation_date timestamp default CURRENT_DATE not null,
    order_date_to timestamp default CURRENT_DATE not null,
    is_sent       boolean default false not null
);

create table if not exists kitchen_order_item
(
    id               bigserial        primary key,
    measure          varchar(255) not null,
    qty              integer not null,
    kitchen_order_id bigint not null
        constraint fk_kitchen_order
            references kitchen_order,
    product_id       bigint not null
        constraint fk_product
            references product
);

CREATE TABLE if not exists role
(
    id   BIGSERIAL    NOT NULL PRIMARY KEY,
    role_name varchar(255) NOT NULL
);

CREATE TABLE if not exists users
(
    id            BIGSERIAL    NOT NULL PRIMARY KEY,
    role_id       int REFERENCES role (id) ON DELETE CASCADE,
    username      varchar(255) NOT NULL,
    password      varchar(255) NOT NULL,
    first_name    varchar(255) NOT NULL,
    last_name     varchar(255) NOT NULL,
    email         varchar(255) NOT NULL,
    personal_id   varchar(255) not null,
    phone_number  varchar(255) not null,
    UNIQUE (username)
);










