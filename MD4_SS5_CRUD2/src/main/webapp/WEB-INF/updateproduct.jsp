<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/8/2023
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit Product</h1>
<form action="<%=request.getContextPath()%>/ProductServelet" method="post">
  <label for="id">id</label>
  <input type="text" id="id" name="id" readonly value="${product.id}">
  <label for="name">Name</label>
  <input type="text" id="name" name="name" value="${product.name}">
  <br>
  <label for="description">Description</label>
  <input type="text" id="description" name="description" value="${product.description}" >
  <br>
  <label for="price">Price</label>
  <input type="text" id="price" name="price" value="${product.price}" >
  <br>
  <label for="quantity">Quantity</label>
  <input type="text" id="quantity" name="quantity"  value="${product.quantity}">
  <br>
  <br>
  <label for="urlFile">Url</label>
  <input type="text" id="urlFile" name="urlFile" value="${product.urlFile}">
  <br>
  <input type="submit" value="EDIT" name="action"/>
</form>
</body>
</html>
