CREATE TABLE product (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100),
    price FLOAT,
    deliveryPrice FLOAT
    );