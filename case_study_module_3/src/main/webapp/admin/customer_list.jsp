<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Customer List</title>
</head>
<body>
<h1>Customer List</h1>
<table border="1">
  <thead>
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Address</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.customerName}</td>
      <td>${customer.customerEmail}</td>
      <td>${customer.customerPhone}</td>
      <td>${customer.customerAddress}</td>
      <td>
        <a href="/admin/customers?action=edit&id=${customer.id}">Edit</a> |
        <a href="/admin/customers?action=delete&id=${customer.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
