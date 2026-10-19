<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - Food Menu</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<% String login = (String) session.getAttribute("login"); %>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
  <h2>Our Food Menu</h2>
  <jsp:include page="Message.jsp"></jsp:include>
  <% if (request.getAttribute("errMsg") != null) { %>
  <div class="alert alert-warning"><%= request.getAttribute("errMsg") %></div>
  <% } %>
  <c:choose>
  <c:when test="${empty list}">
    <div class="empty"><h3>No food items found</h3>
    <% if ("admin".equals(login)) { %><a class="btn btn-gold" href="AddFood.jsp">Add First Food</a><% } %>
    </div>
  </c:when>
  <c:otherwise>
  <div class="grid">
    <c:forEach var="f" items="${list}">
    <div class="card">
      <img src="${f.image}" alt="${f.foodName}" onerror="this.src='images/templatemo_image_01.jpg'">
      <div class="card-body">
        <h3>${f.foodName}</h3>
        <span class="badge">${f.foodType}</span><span class="badge">${f.foodCategory}</span>
        <p>${f.des}</p>
        <div class="price">Rs. ${f.price}</div>
      </div>
      <div class="card-actions">
        <% if ("admin".equals(login)) { %>
          <a class="btn btn-gold" href="FoodServlet?process=updateFood&food_Id=${f.food_Id}">Update</a>
          <a class="btn btn-red" href="FoodServlet?process=deleteFood&food_Id=${f.food_Id}" onclick="return confirm('Delete ${f.foodName}?')">Delete</a>
        <% } else if ("customer".equals(login)) { %>
          <a class="btn btn-green" href="CartServlet?process=addToCart&food_Id=${f.food_Id}">Add to Cart</a>
        <% } %>
      </div>
    </div>
    </c:forEach>
  </div>
  </c:otherwise>
  </c:choose>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
