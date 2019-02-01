create table userorders(
    user_id VARCHAR(3) NOT NULL,
    order_id INT NOT NULL,
    order_total DOUBLE(9,2) NOT NULL
    );

create table items(
    item_id INT NOT NULL,
    item_name VARCHAR(30) NOT NULL,
    item_price DOUBLE(6,2) NOT NULL
    );

create table useritems(
    user_id VARCHAR(3) NOT NULL,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL
    );

create table login(
    user_id VARCHAR(3) NOT NULL,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    cookie VARCHAR(32) NOT NULL
    );
