CREATE TABLE IF NOT EXISTS customer (
    Id 	INT PRIMARY KEY NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS payments (
    Id 	INT PRIMARY KEY NOT NULL,
    transaction_date DATE,
    merchant VARCHAR(50) NULLABLE,
    transaction_amount NUMERIC NOT NULL,
    customer_id INT

    CONSTRAINT FK_customer_id FOREIGN KEY(customer_id)
    REFERENCES customer(Id)
    );