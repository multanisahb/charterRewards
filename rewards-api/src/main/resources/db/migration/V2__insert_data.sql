-- Customer data
INSERT INTO customer(Id, first_name,last_name) VALUES (1, 'Customer 1','Charter');
INSERT INTO customer(Id, first_name,last_name) VALUES (2, 'Customer 2','Charter');

--Payments data for Customer 1
INSERT INTO payments(id, transaction_date, merchant_name, transaction_amount, customer_id)
VALUES (1,'2023-01-31','Charter Food',120.00,1);
INSERT INTO payments(id, transaction_date, merchant_name, transaction_amount, customer_id)
VALUES (2,'2022-12-31','Charter Food',51.55,1);
INSERT INTO payments(id, transaction_date, merchant_name, transaction_amount, customer_id)
VALUES (3,'2023-12-25','Charter Food',80.55,1);

-- Payments data for Customer 2
INSERT INTO payments(id, transaction_date, merchant_name, transaction_amount, customer_id)
VALUES (4,'2023-01-31','Charter Food',10.00,2);
INSERT INTO payments(id, transaction_date, merchant_name, transaction_amount, customer_id)
VALUES (5,'2022-12-31','Charter Food',20.00,2);
INSERT INTO payments(id, transaction_date, merchant_name, transaction_amount, customer_id)
VALUES (6,'2023-12-25','Charter Food',49.50,2);
INSERT INTO payments(id, transaction_date, merchant_name, transaction_amount, customer_id)
VALUES (7,'2023-12-26','Charter Food',50.90,2);

