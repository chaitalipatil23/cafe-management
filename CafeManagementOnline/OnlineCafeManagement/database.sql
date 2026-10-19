-- CafeManagementOnline database setup
-- Run: mysql -uroot -p < database.sql
CREATE DATABASE IF NOT EXISTS basicdb;
USE basicdb;

CREATE TABLE IF NOT EXISTS admin (
  email VARCHAR(60),
  password VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS customer (
  custName VARCHAR(60),
  emailId VARCHAR(60) NOT NULL PRIMARY KEY,
  custPassword VARCHAR(60),
  custAddress VARCHAR(255),
  custNo VARCHAR(20),
  custLocation VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS food (
  foodId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  foodName VARCHAR(60),
  foodType VARCHAR(60),
  foodCategory VARCHAR(60),
  Des VARCHAR(255),
  Price DOUBLE(10,2),
  image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cart (
  cartId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  foodId INT,
  cEmail VARCHAR(60),
  fquantity INT,
  fname VARCHAR(60),
  fprice DOUBLE(10,2),
  totalPrice DOUBLE(10,2)
);

CREATE TABLE IF NOT EXISTS orderfood (
  orderId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  orderDate DATETIME,
  totalBill DOUBLE(10,2),
  emailId VARCHAR(60),
  orderStatus VARCHAR(60)
);

-- Default logins (passwords are plain text, same as the app expects)
INSERT IGNORE INTO admin (email, password) VALUES ('chaitu.com', '12345');
INSERT IGNORE INTO customer (custName, emailId, custPassword, custAddress, custNo, custLocation)
VALUES
  ('chaitali', 'chaitu@gmail.com', '12345', 'kalyan', '98677779999', 'Mumbai'),
  ('teju', 'teju@gmail.com', '12345', 'kalyan', '99999899989', 'thane');

-- Sample menu (images refer to files in src/main/webapp/images/)
INSERT INTO food (foodName, foodType, foodCategory, Des, Price, image) VALUES
  ('Veg Fried Rice', 'veg', 'Chinese', 'Smoky veg fried rice with veggies', 120, 'images/Rice.jpg'),
  ('Chicken Biryani', 'Nonveg', 'Main Course', 'Fragrant chicken biryani with spices', 150, 'images/Rice.jpg'),
  ('Cheesy Garlic Bread', 'veg', 'Fast Food', 'Crispy garlic bread loaded with cheese', 50, 'images/bread.jpeg'),
  ('Punjabi Samosa (2 pc)', 'veg', 'Snacks', 'Crispy samosa with green chutney', 90, 'images/samosa.jpg'),
  ('Pizza Slice', 'veg', 'Fast Food', 'Cheesy pizza slice with veggies', 180, 'images/p.jpeg'),
  ('Red Sauce Pasta', 'veg', 'Italian', 'Penne tossed in spicy tomato sauce', 200, 'images/pasta.jpeg'),
  ('Cheesy Loaded Fries', 'veg', 'Fast Food', 'Crispy fries topped with cheese', 110, 'images/fries.jpeg'),
  ('Steamed Veg Momos (6 pc)', 'veg', 'Chinese', 'Juicy momos with spicy chutney', 90, 'images/dimsum.jpg'),
  ('Vada Pav', 'veg', 'Street Food', 'Mumbai style vada pav', 30, 'images/vadapav.jpg');
