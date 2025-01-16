SET
    client_encoding TO utf8;

create table
    users (
        id bigserial not null,
        username varchar(255) not null,
        password varchar(255) not null
    );

INSERT INTO
    users (username, password)
VALUES
    (
        'admin',
        '$2a$10$9ydEdg8KXa8XJ.JsZR.kb.fAUlOAfEZoQnHQm0imY8vJFyeF4nxEi'
    )