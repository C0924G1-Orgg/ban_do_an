<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Dashboard</title>
</head>
<body>
<h2>Welcome, ${sessionScope.loggedInCustomer.name}</h2>
<p>Email: ${sessionScope.loggedInCustomer.email}</p>
<a href="customers?action=list">Manage Customers</a>
</body>
</html>