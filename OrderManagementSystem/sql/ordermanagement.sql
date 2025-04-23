CREATE DATABASE IF NOT EXISTS ordermanagement;
USE ordermanagement;

CREATE TABLE IF NOT EXISTS users (
    userId INT PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100),
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS products (
    productId INT PRIMARY KEY,
    productName VARCHAR(100),
    description TEXT,
    price DOUBLE,
    quantityInStock INT,
    type VARCHAR(50),
    brand VARCHAR(100),
    warrantyPeriod INT,
    size VARCHAR(10),
    color VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS orders (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    productId INT,
    FOREIGN KEY (userId) REFERENCES users(userId),
    FOREIGN KEY (productId) REFERENCES products(productId)
);

