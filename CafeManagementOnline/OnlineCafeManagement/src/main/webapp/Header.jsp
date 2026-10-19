<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String login = (String) session.getAttribute("login");
String username = (String) session.getAttribute("username");
String pageName = request.getRequestURI();
%>
<link rel="stylesheet" href="assets/style.css">
<nav class="navbar">
  <a class="brand" href="Myindex.jsp">
    <img src="images/logo.png" alt="logo" onerror="this.src='images/templatemo_image_01.jpg'">
    <span><h1>Cafe Aroma<small>FRESH &amp; TASTY</small></h1></span>
  </a>
  <ul class="nav-links">
    <li><a href="Myindex.jsp" class="<%= pageName.endsWith("Myindex.jsp") ? "active" : "" %>">Home</a></li>
    <li><a href="FoodServlet?process=allfood">Food Menu</a></li>
    <% if ("admin".equals(login)) { %>
      <li><a href="AddFood.jsp">Add Food</a></li>
    <% } %>
    <% if ("customer".equals(login)) { %>
      <li><a href="CartServlet?process=ShowCart">My Cart</a></li>
      <li><a href="OrderServlet?process=myOrders">My Orders</a></li>
    <% } %>
    <% if (login == null) { %>
      <li><a href="Login.jsp">Login</a></li>
      <li><a href="AddCustomer.jsp">Register</a></li>
    <% } else { %>
      <li><span class="user-chip">Hi, <%= username %></span></li>
      <li><a href="LoginServlet?action=logout">Logout</a></li>
    <% } %>
  </ul>
</nav>
