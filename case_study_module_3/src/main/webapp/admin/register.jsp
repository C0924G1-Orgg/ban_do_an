<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="customers?action=register" method="post">
  <label>Name:</label>
  <input type="text" name="name" required>
  <br>
  <label>Email:</label>
  <input type="email" name="email" required>
  <br>
  <label>Password:</label>
  <input type="password" name="password" required>
  <br>
  <label>Phone:</label>
  <input type="text" name="phone">
  <br>
  <label>Address:</label>
  <input type="text" name="address">
  <br>
  <input type="submit" value="Register">
</form>
<c:if test="${not empty error}">
  <p style="color: red;">${error}</p>
</c:if>
</body>
</html>
