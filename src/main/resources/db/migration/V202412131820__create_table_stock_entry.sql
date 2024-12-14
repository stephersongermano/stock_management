SET
    client_encoding TO utf8;

create table
    stock_entry (
        id bigserial not null,
        entry_date timestamp(6) not null,
        note_number integer not null,
        total_value numeric(38, 2) not null,
        primary key (id)
    );