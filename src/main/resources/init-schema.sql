drop table if exists school_group;
create table if not exists school_group
(
    id   serial primary key,
    name varchar(25) not null

);

drop table if exists kitchen_order;
create table if not exists kitchen_order
(
    id            serial primary key,
    group_id      int         not null,
    creation_date timestamp   not null default current_date,
    order_date_to timestamp   not null default current_date,
    status        varchar(25) not null,
    CONSTRAINT fk_group
        FOREIGN KEY (group_id)
            REFERENCES school_group (id)
);

