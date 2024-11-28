SET
    client_encoding TO utf8;

create table
    ingredient (
        id bigserial not null,
        name varchar(255) not null,
        price numeric(38, 2) not null,
        quantity integer not null,
        primary key (id)
    );