<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/utils/bootstrap.jsp" %>
<%@ include file="/WEB-INF/view/utils/navbar.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách món ăn</title>
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Danh sách món ăn</h2>

    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Tên món ăn</th>
            <th>Mô tả</th>
            <th>Giá</th>
            <th>Nhà hàng</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="food" items="${foods}">
            <tr>
                <td>${food.food_id}</td>
                <td>${food.food_name}</td>
                <td>${food.food_description}</td>
                <td>${food.food_price}</td>
                <td>${food.restaurant_name}</td>
                <td>
                    <a href="/food?action=update&id=${food.food_id}" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="/food?action=delete&id=${food.food_id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa món ăn này?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/food?action=create" class="btn btn-primary">Thêm món ăn mới</a>
</div>
</body>
</html>
