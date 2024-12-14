SET
    client_encoding TO utf8;

create table
    stock_entry_ingredients (
        stock_entry_id bigint not null,
        ingredient_id bigint not null,
        primary key (stock_entry_id, ingredient_id),
        constraint fk_stock_entry foreign key (stock_entry_id) references stock_entry (id) ON DELETE CASCADE,
        constraint fk_ingredient foreign key (ingredient_id) references ingredient (id) ON DELETE CASCADE
    );