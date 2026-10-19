<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - Login</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
  <div class="panel">
    <h2>Login</h2>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="LoginServlet" method="post">
      <div class="field"><label>Email Id</label><input type="text" name="email" required></div>
      <div class="field"><label>Password</label><input type="password" name="password" required></div>
      <input type="submit" value="Login" class="btn btn-green">
      <input type="reset" value="Clear" class="btn btn-dark">
    </form>
    <p>New customer? <a href="AddCustomer.jsp">Register here</a></p>
  </div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
