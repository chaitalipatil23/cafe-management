<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - Register</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
  <div class="panel">
    <h2>Customer Registration</h2>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="CustomerServlet" method="post">
      <input type="hidden" name="process" value="Register">
      <div class="field"><label>Name</label><input type="text" name="custName" required></div>
      <div class="field"><label>Email Id</label><input type="email" name="emailId" required></div>
      <div class="field"><label>Password</label><input type="password" name="custPassword" required></div>
      <div class="field"><label>Address</label><input type="text" name="custAddress"></div>
      <div class="field"><label>Phone No</label><input type="text" name="custNo"></div>
      <div class="field"><label>Location</label><input type="text" name="custLocation"></div>
      <input type="submit" value="Register" class="btn btn-green">
      <input type="reset" value="Clear" class="btn btn-dark">
    </form>
  </div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
