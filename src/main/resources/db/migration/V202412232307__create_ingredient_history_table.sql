SET
    client_encoding TO utf8;

create table
    ingredient_history (
        id bigserial not null,
        name varchar(255) not null,
        price numeric(38, 2) not null,
        quantity integer not null,
        brand varchar(255) not null,
        created_at timestamp(6) not null,
        ingredient_id bigint,
        stock_entry_id bigint,
        primary key (id),
        constraint fk_history_ingredient_ingredient foreign key (ingredient_id) references ingredient (id) on DELETE CASCADE,
        constraint fk_history_ingredient_stock_entry foreign key (stock_entry_id) references stock_entry (id) on DELETE set null
    );

drop table stock_entry_ingredients;

create table
    stock_entry_ingredients (
        stock_entry_id bigint not null,
        ingredient_history_id bigint not null,
        primary key (stock_entry_id, ingredient_history_id),
        constraint fk_stock_entry foreign key (stock_entry_id) references stock_entry (id) on DELETE CASCADE,
        constraint fk_ingredient_history foreign key (ingredient_history_id) references ingredient_history (id) on DELETE CASCADE
    );