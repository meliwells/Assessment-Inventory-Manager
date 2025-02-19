CREATE DATABASE InventoryManager;

USE InventoryManager;

CREATE TABLE product (
	Product_ID int primary key auto_increment,
    Product_Name varchar(125) not null,
    Quantity int not null,
    Price decimal(9 , 2 ) not null default 0,
    Expiration_Date date null
    );
    
    INSERT INTO Product (Product_Name, Quantity, Price, Expiration_Date) 
    VALUES
    ('Flour', 10, 2.25, null),
	('Sugar', 8, 2.00, null),
    ('Eggs', 5, 4.50, '2025-03-15'),
    ('Butter', 10, 4.25, null);




