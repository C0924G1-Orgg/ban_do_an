<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/utils/bootstrap.jsp" %>
<%@ include file="/WEB-INF/view/utils/navbar.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm món ăn mới</title>
</head>
<body>
<c:if test="${not empty error}">
    <div style="color: red; font-weight: bold; margin-bottom: 10px;">
            ${error}
    </div>
</c:if>
<div class="container mt-4">
    <h1 class="mb-4">Thêm món ăn mới</h1>

    <form action="/food" method="post">
        <input type="hidden" name="action" value="create">
        <div class="mb-3">
            <label for="name" class="form-label">Tên món ăn:</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Mô tả:</label>
            <textarea id="description" name="description" class="form-control" required></textarea>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Giá:</label>
            <input type="number" id="price" name="price" class="form-control" step="0.01" required>
        </div>
        <div class="mb-3">
            <label for="restaurantId" class="form-label">Nhà hàng:</label>
            <select id="restaurantId" name="restaurantId" class="form-select" required>
                <c:forEach var="restaurant" items="${restaurants}">
                    <option value="${restaurant.restaurant_id}">${restaurant.restaurant_name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Thêm món ăn</button>
    </form>
    <br>
    <a href="/food" class="btn btn-secondary">Quay lại danh sách</a>
</div>
</body>
</html>
