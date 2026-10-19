<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - My Cart</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
  <h2>My Cart</h2>
  <jsp:include page="Message.jsp"></jsp:include>
  <c:choose>
  <c:when test="${empty clist}">
    <div class="empty"><h3>Your cart is empty</h3><a class="btn btn-gold" href="FoodServlet?process=allfood">Browse Food Menu</a></div>
  </c:when>
  <c:otherwise>
  <form action="OrderServlet" method="post">
  <input type="hidden" name="process" value="PlaceOrder">
  <table class="data">
    <tr><th>Food</th><th>Price</th><th>Qty</th><th>Sub Total</th><th>Action</th></tr>
    <c:forEach var="c" items="${clist}">
    <tr>
      <td>${c.fname}</td>
      <td>Rs. ${c.fprice}</td>
      <td>${c.fquantity}</td>
      <td>Rs. ${c.totalPrice}</td>
      <td><a class="btn btn-red" href="CartServlet?process=removeFromCart&cartId=${c.cartid}">Remove</a></td>
    </tr>
    </c:forEach>
    <tr><td colspan="5"><input type="submit" value="Place Order" class="btn btn-green"></td></tr>
  </table>
  </form>
  </c:otherwise>
  </c:choose>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
