<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh Toán - Food</title>
    <jsp:include page="/partials/head.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="/css/cart.css">

</head>
<body>
<div class="collapse d-md-block sidebar" id="sidebar">
    <div>
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="logo" class="img-logo">
        </div>
        <nav>
            <a class="nav-link" href="${pageContext.request.contextPath}/">TRANG CHỦ</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/thuc-don">THỰC ĐƠN</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/khuyen-mai">KHUYẾN MÃI</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/lien-he">LIÊN HỆ</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/gioi-thieu">GIỚI THIỆU</a>
        </nav>
    </div>
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

<div class="main-content">
    <div class="banner">
        <h1 style="text-align: left">THANH TOÁN</h1>
    </div>

    <h1>Thông tin thanh toán</h1>

    <c:choose>
        <c:when test="${not empty cartItems}">
            <form action="${pageContext.request.contextPath}/payment?action=process" method="post">
                <table border="1" cellpadding="10" cellspacing="0" style="width: 100%; text-align: center;">
                    <thead>
                    <tr>
                        <th>Hình ảnh</th>
                        <th>Tên sản phẩm</th>
                        <th>Đơn giá</th>
                        <th>Số lượng</th>
                        <th>Tổng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td><img src="${item.image}" alt="${item.name}" width="100"></td>
                            <td>${item.name}</td>
                            <td><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="VND"/></td>
                            <td>${item.quantity}</td>
                            <td><fmt:formatNumber value="${item.totalPrice}" type="currency" currencySymbol="VND"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <h3 style="text-align: right;">Tổng cộng: <span id="total-price"><fmt:formatNumber value="${total}" type="currency" currencySymbol="VND"/></span></h3>

                <h3>Thông tin thanh toán</h3>
                <div class="form-group">
                    <label for="fullName">Họ và tên</label>
                    <input type="text" id="fullName" name="fullName" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại</label>
                    <input type="text" id="phone" name="phone" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="address">Địa chỉ</label>
                    <input type="text" id="address" name="address" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="paymentMethod">Phương thức thanh toán</label>
                    <select id="paymentMethod" name="paymentMethod" class="form-control">
                        <option value="cash">Thanh toán khi nhận hàng</option>
                        <option value="bank">Chuyển khoản ngân hàng</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">Thanh toán</button>
            </form>
        </c:when>
        <c:otherwise>
            <p>Giỏ hàng của bạn hiện tại không có sản phẩm nào.</p>
        </c:otherwise>
    </c:choose>

    <div style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/thuc-don" class="btn btn-secondary">Quay lại thực đơn</a>
    </div>

    <jsp:include page="/partials/footer.jsp"/>
</div>

<div class="cart-icon">
    <a href="${pageContext.request.contextPath}/cart?action=view">
        <i class="bi bi-cart"></i>
        <span class="badge bg-danger">${cartSize}</span>
    </a>
</div>

<script>
    function formatCurrency(amount) {
        var numberFormat = new Intl.NumberFormat('vi-VN');
        return numberFormat.format(amount) + " VND";
    }
</script>
</body>
</html>
