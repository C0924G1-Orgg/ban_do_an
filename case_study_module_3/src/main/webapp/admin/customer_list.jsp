<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer List</title>
</head>
<body>
<h2>Customer List</h2>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Address</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.customerId}</td>
      <td>${customer.name}</td>
      <td>${customer.email}</td>
      <td>${customer.phone}</td>
      <td>${customer.address}</td>
      <td>
        <a href="customers?action=edit&id=${customer.customerId}">Edit</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>