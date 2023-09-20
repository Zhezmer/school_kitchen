drop table if exists school_group;
create table if not exists school_group
(
    id   serial primary key,
    name varchar(25) not null,
    UNIQUE (name)


);

drop table if exists kitchen_order;
create table if not exists kitchen_order
(
    id            serial primary key,
    group_id      int       not null,
    creation_date timestamp not null default current_date,
    order_date_to timestamp not null default current_date,

    CONSTRAINT fk_group
        FOREIGN KEY (group_id)
            REFERENCES school_group (id)
);
drop table if exists product;
create table if not exists product
(
    id   serial primary key,
    name varchar(25) not null
);
drop table if exists kitchen_order_product;
create table if not exists kitchen_order_product
(
    id              serial primary key,
    kitchen_order_id        int         not null,
    product_id      int         not null,
    quantity        int         not null,
    unit_of_measure varchar(25) not null,
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
            REFERENCES product (id),
    CONSTRAINT fk_order
        FOREIGN KEY (kitchen_order_id)
            REFERENCES kitchen_order (id)

);
