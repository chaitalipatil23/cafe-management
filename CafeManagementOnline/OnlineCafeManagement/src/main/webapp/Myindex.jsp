<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - Home</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Message.jsp"></jsp:include>
<div class="container">
  <div class="hero">
    <h2>Welcome to Cafe Aroma</h2>
    <p>Freshly brewed coffee, delicious snacks and meals made with love.</p>
    <p>
      <a class="btn btn-gold" href="FoodServlet?process=allfood">View Food Menu</a>
      <% if (session.getAttribute("login") == null) { %>
        <a class="btn btn-green" href="AddCustomer.jsp">Join Now</a>
      <% } %>
    </p>
  </div>
  <div class="grid">
    <div class="card">
      <img src="images/samosa.jpg" alt="Samosa">
      <div class="card-body"><h3>Crispy Samosa</h3><p>Golden punjabi samosa served with green chutney.</p></div>
    </div>
    <div class="card">
      <img src="images/p.jpeg" alt="Pizza">
      <div class="card-body"><h3>Cheesy Pizza</h3><p>Hot cheesy pizza slice loaded with veggies.</p></div>
    </div>
    <div class="card">
      <img src="images/fries.jpeg" alt="Fries">
      <div class="card-body"><h3>Evening Snacks</h3><p>Crispy fries, garlic bread and vada pav for evenings.</p></div>
    </div>
    <div class="card">
      <img src="images/Rice.jpg" alt="Biryani">
      <div class="card-body"><h3>Special Meals</h3><p>Biryani, fried rice, pasta and momos at best prices.</p></div>
    </div>
  </div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
