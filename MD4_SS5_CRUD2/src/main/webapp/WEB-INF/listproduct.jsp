<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/7/2023
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List-Product</title>
</head>
<body>
<h1>List Product</h1>
<form action="<%=request.getContextPath()%>/ProductServelet">
  <input type="text" name="search-name" value="${keyword}">
  <br>
  <label>Sort</label>
  <%
    if(request.getAttribute("sort")!=null)
      System.out.println(request.getAttribute("sort"));
    if(request.getAttribute("sort")!=null)
      System.out.println(request.getAttribute("sort"));
  %>
  <select name="sort" >
    <option value="name" selected="<%=request.getAttribute("sort")!=null&&request.getAttribute("sort").equals("name")%>">Name</option>
    <option value="email" selected="<%=request.getAttribute("sort")!=null&&request.getAttribute("sort").equals("email")%>">Email</option>
    <option value="address" selected="<%=request.getAttribute("sort")!=null&&request.getAttribute("sort").equals("address")%>">Address</option>
  </select>
  <br>
  <label>By</label>
  <select name="by" >
    <option value="ASC">Tăng Dần</option>
    <option value="DESC">Giảm Dần</option>
  </select>
  <br>
  <input type="submit" value="SEARCH" name="action">
</form>
<a href="<%=request.getContextPath()%>/ProductServelet?action=ADD">Add</a>
<table border="10" cellpadding="20" cellspacing="10">
  <thead>
  <tr>
    <th>STT</th>
    <th>Name</th>
    <th>Image</th>
    <th>Des</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Status</th>
    <th colspan="2">Action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${product}" var="p" varStatus="item">
    <tr>
      <td>${item.count}</td>
      <td>${p.name}</td>
      <td>${p.urlFile}</td>
      <td>${p.description}</td>
      <td>${p.price}</td>
      <td>${p.quantity}</td>
      <td>${p.status? "Hết Hàng":"Đang Bán"}</td>
      <td><a href="<%=request.getContextPath()%>/ProductServelet?action=EDIT&id=${p.id}">Edit</a></td>
      <td><a onclick="return confirm('do you want to delete this item ? ')" href="<%=request.getContextPath()%>/ProductServelet?action=DELETE&id=${p.id}">Delete</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
