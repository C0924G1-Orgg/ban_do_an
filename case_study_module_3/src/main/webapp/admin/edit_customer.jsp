<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Customer</title>
</head>
<body>
<h2>Edit Customer</h2>
<form action="customers" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="id" value="${customer.customerId}">

  <label for="name">Name:</label>
  <input type="text" id="name" name="name" value="${customer.name}" required><br>

  <label for="email">Email:</label>
  <input type="email" id="email" name="email" value="${customer.email}" required><br>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br>

  <label for="phone">Phone:</label>
  <input type="text" id="phone" name="phone" value="${customer.phone}" required><br>

  <label for="address">Address:</label>
  <input type="text" id="address" name="address" value="${customer.address}" required><br>

  <label for="isAdmin">Admin:</label>
  <input type="checkbox" id="isAdmin" name="isAdmin" ${customer.isAdmin ? 'checked' : ''}><br>

  <button type="submit">Update</button>
  <p style="color:red;">${error}</p>
</form>
</body>
</html>
