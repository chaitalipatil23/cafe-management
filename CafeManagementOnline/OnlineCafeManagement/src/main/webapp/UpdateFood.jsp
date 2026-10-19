<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Aroma - Update Food</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
  <div class="panel">
    <h2>Update Food</h2>
    <form action="FoodServlet" method="post">
      <input type="hidden" name="process" value="editFood">
      <div class="field"><label>Id</label><input type="text" name="food_Id" value="${fobj.food_Id}" readonly></div>
      <div class="field"><label>Name</label><input type="text" name="foodName" value="${fobj.foodName}" required></div>
      <div class="field"><label>Type</label><input type="text" name="foodType" value="${fobj.foodType}"></div>
      <div class="field"><label>Category</label><input type="text" name="foodCategory" value="${fobj.foodCategory}"></div>
      <div class="field"><label>Description</label><input type="text" name="des" value="${fobj.des}"></div>
      <div class="field"><label>Price (Rs.)</label><input type="number" step="0.01" min="0" name="price" value="${fobj.price}" required></div>
      <div class="field"><label>Food Photo</label>
        <select name="image" id="foodImage" onchange="document.getElementById('preview').src=this.value">
          <option value="${fobj.image}" selected>Current photo (keep)</option>
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
      <div class="field"><img id="preview" src="${fobj.image}" onerror="this.src='images/templatemo_image_01.jpg'" style="width:200px;border-radius:10px"></div>
      <input type="submit" value="Update" class="btn btn-gold">
      <a href="FoodServlet?process=allfood" class="btn btn-dark">Cancel</a>
    </form>
  </div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
