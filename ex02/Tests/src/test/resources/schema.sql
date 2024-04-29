CREATE SCHEMA IF NOT EXISTS butik;

CREATE TABLE IF NOT EXISTS butik.product (
identifier INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
name VARCHAR(20),
price INTEGER);