create table if not exists item (
    item_id bigserial primary key,
    item_name varchar(100) not null,
    item_price bigint not null,
    item_manufacturer_id bigint not null
);

create table if not exists manufacturer (
    manufacturer_id bigserial primary key,
    manufacturer_name varchar(100) not null,
    manufacturer_address varchar(100) not null,
    manufacturer_phone varchar(10) not null
);
