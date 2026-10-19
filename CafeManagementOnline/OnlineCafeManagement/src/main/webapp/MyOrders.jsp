<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - My Orders</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
  <h2>My Orders</h2>
  <jsp:include page="Message.jsp"></jsp:include>
  <c:choose>
  <c:when test="${empty olist}">
    <div class="empty"><h3>No orders placed yet</h3><a class="btn btn-gold" href="FoodServlet?process=allfood">Order Something Tasty</a></div>
  </c:when>
  <c:otherwise>
  <table class="data">
    <tr><th>Order Id</th><th>Date</th><th>Total Bill</th><th>Status</th></tr>
    <c:forEach var="o" items="${olist}">
    <tr>
      <td>#${o.orderId}</td>
      <td>${o.orderDate}</td>
      <td>Rs. ${o.totalBill}</td>
      <td><span class="badge">${o.orderStatus}</span></td>
    </tr>
    </c:forEach>
  </table>
  </c:otherwise>
  </c:choose>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
