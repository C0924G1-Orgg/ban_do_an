<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow-sm">
        <div class="card-header bg-success text-white">
          <h2 class="text-center mb-0">Register</h2>
        </div>
        <div class="card-body">
          <form action="customers" method="post">
            <input type="hidden" name="action" value="register">

            <div class="mb-3">
              <label for="name" class="form-label">Name:</label>
              <input type="text" id="name" name="name" class="form-control" required>
            </div>

            <div class="mb-3">
              <label for="email" class="form-label">Email:</label>
              <input type="email" id="email" name="email" class="form-control" required>
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Password:</label>
              <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <div class="mb-3">
              <label for="phone" class="form-label">Phone:</label>
              <input type="text" id="phone" name="phone" class="form-control" required>
            </div>

            <div class="mb-3">
              <label for="address" class="form-label">Address:</label>
              <input type="text" id="address" name="address" class="form-control" required>
            </div>

            <input type="hidden" name="isAdmin" value="false">

            <div class="d-grid">
              <button type="submit" class="btn btn-success">Register</button>
            </div>

            <p class="text-danger mt-3">${error}</p>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
