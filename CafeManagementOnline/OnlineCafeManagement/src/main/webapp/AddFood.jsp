<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - Add Food</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
  <div class="panel">
    <h2>Add New Food</h2>
    <% if (request.getAttribute("errmsg") != null) { %>
    <div class="alert alert-danger"><%= request.getAttribute("errmsg") %></div>
    <% } %>
    <form action="FoodServlet" method="post">
      <input type="hidden" name="process" value="Addfood">
      <div class="field"><label>Food Name</label><input type="text" name="foodName" required></div>
      <div class="field"><label>Food Type (veg / Nonveg)</label><input type="text" name="foodType"></div>
      <div class="field"><label>Food Category</label><input type="text" name="foodCategory"></div>
      <div class="field"><label>Description</label><input type="text" name="Des"></div>
      <div class="field"><label>Price (Rs.)</label><input type="number" step="0.01" min="0" name="Price" required></div>
      <div class="field"><label>Food Photo</label>
        <select name="image" id="foodImage" onchange="document.getElementById('preview').src=this.value">
          <option value="images/Rice.jpg">Biryani / Rice</option>
          <option value="images/samosa.jpg">Samosa</option>
          <option value="images/pasta.jpeg">Pasta</option>
          <option value="images/p.jpeg">Chicken Curry</option>
          <option value="images/bread.jpeg">Bread / Maggi</option>
          <option value="images/fries.jpeg">Fries</option>
          <option value="images/dimsum.jpg">Dimsum</option>
          <option value="images/vadapav.jpg">Vada Pav</option>
        </select>
      </div>
      <div class="field"><img id="preview" src="images/Rice.jpg" style="width:200px;border-radius:10px"></div>
      <input type="submit" value="Add Food" class="btn btn-green">
      <input type="reset" value="Clear" class="btn btn-dark">
    </form>
  </div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
