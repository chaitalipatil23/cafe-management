# Cafe Aroma - Online Cafe Management (Java JSP/Servlet)

A simple online food ordering web app built with Java Servlets, JSP, JDBC and MySQL,
running on Apache Tomcat 9.

## Features
- Admin login: add / update / delete food items with photos
- Customer registration + login
- Food menu with photos, add-to-cart, cart page, place order, order history
- Logout, success/error messages on every action

## Default logins
| Role | Email | Password |
|---|---|---|
| Admin | chaitu.com | 12345 |
| Customer | chaitu@gmail.com | 12345 |
| Customer | teju@gmail.com | 12345 |

## Run locally (Windows)
1. Install JDK 8+ and MySQL 8, make sure MySQL is running.
2. Create the database: `mysql -uroot -p < CafeManagementOnline/OnlineCafeManagement/database.sql`
3. If your MySQL root password is not `chaitali2312`, update it in
   `CafeManagementOnline/OnlineCafeManagement/src/main/java/Utility/DBUtility.java`.
4. Download Tomcat 9 and extract it next to this README as `apache-tomcat-9.0.99`
   (this project uses `javax.servlet`, so Tomcat 10+ will NOT work).
5. Double-click `run-cafe.bat` - it compiles the sources and starts the server.
6. Open http://localhost:8080/cafe/Login.jsp

## Deploy the WAR on any Tomcat 9 server
1. Double-click `build-war.bat` - it creates `cafe.war`.
2. Copy `cafe.war` into the server's `webapps/` folder and start Tomcat.
3. Create the database on that server with `database.sql`.
4. Open http://server:8080/cafe/Login.jsp

## Project structure
```
CafeManagementOnline/OnlineCafeManagement/
  src/main/java/Controller/  Login, Food, Cart, Customer, Order servlets
  src/main/java/IMPL/        DAO implementations (JDBC)
  src/main/java/Dao/         DAO interfaces
  src/main/java/POJO/        Food, Cart, Customer, OrderFood, Login beans
  src/main/java/Utility/     DBUtility (DB connection)
  src/main/webapp/           JSP pages, assets/style.css, images/
  database.sql               Full DB + sample data
```
