CREATE DATABASE InventoryManager;

USE InventoryManager;

CREATE TABLE Product (
	Product_ID INT PRIMARY KEY AUTO_INCREMENT,
    Product_Name varchar(125) not null,
    Quantity int not null,
    Price DECIMAL(9 , 2 ) NOT NULL DEFAULT 0,
    Expiration_Date Date
    );
    
    INSERT INTO Product (Product_Name, Quantity, Price, Expiration_Date) VALUES
    (Flour, 10, 2.25),
	(Sugar, 8, 2.00),
    (Eggs, 5, 4.50, 2025-03-15),
    (Butter, 10, 4.25);



