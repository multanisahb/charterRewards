CREATE TABLE IF NOT EXISTS customer (
    Id INTEGER NOT NULL UNIQUE PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS payments (
    Id INTEGER NOT NULL UNIQUE PRIMARY KEY,
    transaction_date DATE,
    merchant_name VARCHAR(50) DEFAULT NULL,
    transaction_amount NUMERIC NOT NULL,
    customer_id INTEGER,
    CONSTRAINT FK_customer_id
    FOREIGN KEY(customer_id)
    REFERENCES customer(Id)
    ON DELETE SET NULL
    ON UPDATE SET NULL
    );