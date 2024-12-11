SET
    client_encoding TO utf8;

alter table ingredient
add column created_at timestamp(6) not null,
add column updated_at timestamp(6),
add column deleted_at timestamp(6),
add column deleted boolean;