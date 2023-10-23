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
create table kitchen_order_item
(
    id               bigserial
        primary key,
    measure          varchar(255),
    qty              integer not null,
    kitchen_order_id bigint
        constraint fkch483g47dr58rm8xkdpki4u6y
            references public.kitchen_order,
    product_id       bigint
        constraint uk_h8vsl1f819tg4fytla96h40ac
            unique
        constraint fkfwf417jpl4hyqa4pcs4yl6e1i
            references public.product
);

alter table public.kitchen_order_item
    owner to "user";


-- ALTER TABLE public.kitchen_order_item DROP CONSTRAINT fkch483g47dr58rm8xkdpki4u6y;


