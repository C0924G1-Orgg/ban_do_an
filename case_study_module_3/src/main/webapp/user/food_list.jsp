<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/partials/head.jsp"/>
<body>
<!-- Sidebar -->
<div class="collapse d-md-block sidebar" id="sidebar">
    <div>
        <!-- Logo -->
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="logo" class="img-logo">
        </div>
        <!-- Navigation -->
        <nav>
            <a class="nav-link" href="${pageContext.request.contextPath}/">TRANG CHỦ</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/thuc-don">THỰC ĐƠN</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/khuyen-mai">KHUYẾN MÃI</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/lien-he">LIÊN HỆ</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/gioi-thieu">GIỚI THIỆU</a>
        </nav>
    </div>
    <!-- Contact Info -->
    <div class="contact-info">
        <p><i class="fas fa-phone-alt"></i> 0901234567</p>
        <a href="https://facebook.com" target="_blank">
            <i class="fab fa-facebook" style="color: blue;"></i> Facebook
        </a>
        <a href="https://instagram.com" target="_blank">
            <i class="fab fa-instagram" style="color: #912b42"></i> Instagram
        </a>
    </div>
</div>
<!-- Main Content -->
<div class="main-content">
    <div class="banner">
        <h1 style="font-size: 30px">THỰC ĐƠN HÔM NAY</h1>
    </div>
    <div class="food-menu">
        <div class="container">
            <h2>THỰC ĐƠN</h2>
            <c:choose>
                <c:when test="${empty foods}">
                    <p class="text-center">Hiện chưa có món ăn nào trong thực đơn.</p>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <c:forEach var="food" items="${foods}">
                            <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                                <div class="card">
                                    <img src="${food.image}" class="card-img-top" alt="${food.foodName}">
                                    <div class="card-body">
                                        <h5 class="card-title">${food.foodName}</h5>
                                        <p class="card-text">${food.foodDescription}</p>
                                        <p class="card-text">Giá:
                                            <fmt:formatNumber value="${food.foodPrice}" type="currency" currencySymbol="VND"/>
                                        </p>
                                        <form action="${pageContext.request.contextPath}/cart?action=add" method="post">
                                            <input type="hidden" name="id" value="${food.foodId}">
                                            <input type="hidden" name="name" value="${food.foodName}">
                                            <input type="hidden" name="image" value="${food.image}">
                                            <input type="hidden" name="price" value="${food.foodPrice}">
                                            <input type="hidden" name="quantity" value="1"> <!-- Mặc định số lượng là 1 -->
                                            <button type="submit" class="btn btn-primary w-100">Thêm vào giỏ</button>
                                        </form>
                                        <a href="${pageContext.request.contextPath}/user/food_details?id=${food.foodId}"
                                           class="btn btn-secondary w-100 mt-2">Chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!-- Cart Icon -->
    <div class="cart-icon">
        <a href="${pageContext.request.contextPath}/cart?action=view">
            <i class="bi bi-cart"></i>
            <span class="badge bg-danger">${cartSize}</span>
        </a>
    </div>
    <jsp:include page="/partials/footer.jsp" />
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
