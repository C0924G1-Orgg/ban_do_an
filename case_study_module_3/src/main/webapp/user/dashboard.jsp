<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Dashboard</title>
</head>
<body>
<h1>Welcome, ${sessionScope.loggedInCustomer.customerName}</h1>
<p>Email: ${sessionScope.loggedInCustomer.customerEmail}</p>
<p>Phone: ${sessionScope.loggedInCustomer.customerPhone}</p>
<p>Address: ${sessionScope.loggedInCustomer.customerAddress}</p>

<form action="/user/customers" method="post">
  <input type="hidden" name="action" value="update">
  <input type="text" name="name" value="${sessionScope.loggedInCustomer.customerName}" required/>
  <input type="text" name="phone" value="${sessionScope.loggedInCustomer.customerPhone}" required/>
  <input type="text" name="address" value="${sessionScope.loggedInCustomer.customerAddress}" required/>
  <button type="submit">Update</button>
</form>

<form action="/user/customers?action=logout" method="get">
  <button type="submit">Logout</button>
</form>
</body>
</html>
