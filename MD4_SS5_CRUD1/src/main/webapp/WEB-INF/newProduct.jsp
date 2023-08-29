<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/8/2023
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>New Product</h1>
<form action="<%=request.getContextPath()%>/ProductServelet" method="post">
  <label for="name">Name</label>
  <input type="text" id="name" name="name" >
  <br>
  <label for="description">Description</label>
  <input type="text" id="description" name="description" >
  <br>
  <label for="price">Price</label>
  <input type="text" id="price" name="price" >
  <br>
  <label for="quantity">Quantity</label>
  <input type="text" id="quantity" name="quantity" >
  <br>
  <br>
  <label for="urlFile">Url</label>
  <input type="text" id="urlFile" name="urlFile">
  <br>
  <input type="submit" value="ADD" name="action"/>
</form>
</body>
</html>
