CREATE DATABASE InventoryManager;

USE InventoryManager;

CREATE TABLE Product (
	Product_ID INT PRIMARY KEY AUTO_INCREMENT,
    Product_Name varchar(125) not null,
    Quantity int not null,
    Price DECIMAL(9 , 2 ) NOT NULL DEFAULT 0
    );
    
    INSERT INTO Product (Product_Name, Quantity, Price) VALUES
    (Flour, 10, 2.25),
	(Sugar, 8, 2.00),
    (Eggs, 5, 4.50),
    (Butter, 10, 4.25);


SELECT * FROM Product;
