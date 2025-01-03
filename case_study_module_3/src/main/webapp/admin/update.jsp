<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật món ăn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${not empty error}">
    <div style="color: red; font-weight: bold; margin-bottom: 10px;">
            ${error}
    </div>
</c:if>

<div class="container mt-4">
    <h1 class="mb-4">Cập nhật món ăn</h1>

    <form action="/food" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${food.foodId}">
        <div class="mb-3">
            <label for="name" class="form-label">Tên món ăn:</label>
            <input type="text" id="name" name="name" class="form-control" value="${food.foodName}" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Mô tả:</label>
            <textarea id="description" name="description" class="form-control" required>${food.foodDescription}</textarea>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Giá:</label>
            <input type="number" id="price" name="price" class="form-control" step="0.01" value="${food.foodPrice}" required>
        </div>
        <div class="mb-3">
            <label for="restaurantId" class="form-label">Nhà hàng:</label>
            <select id="restaurantId" name="restaurantId" class="form-select" required>
                <c:forEach var="restaurant" items="${restaurants}">
                    <option value="${restaurant.restaurantId}" ${restaurant.restaurantId == food.restaurantId ? 'selected' : ''}>
                            ${restaurant.restaurantName}
                    </option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật món ăn</button>
    </form>
    <br>
    <a href="/food" class="btn btn-secondary">Quay lại danh sách</a>
</div>
</body>
</html>