create sequence product_seq start with 1 increment by 50;
create table product (price float(53) not null, quantity integer not null, id bigint not null, name varchar(255) not null unique, primary key (id));
