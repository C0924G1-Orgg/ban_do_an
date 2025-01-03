<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ Hàng - Food</title>
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
        <h1 style="text-align: left">GIỎ HÀNG</h1>
    </div>

    <h1>Giỏ Hàng</h1>

    <c:choose>
        <c:when test="${not empty cartItems}">
            <table border="1" cellpadding="10" cellspacing="0" style="width: 100%; text-align: center;">
                <thead>
                <tr>
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Đơn giá</th>
                    <th>Số lượng</th>
                    <th>Tổng</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td><img src="${item.image}" alt="${item.name}" width="100"></td>
                        <td>${item.name}</td>
                        <td><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="VND"/></td>
                        <td>
                            <select class="quantity-select" data-id="${item.id}" onchange="updateCart(${item.id}, this.value)" style="width: 70px;">
                                <c:forEach begin="1" end="10" var="i">
                                    <option value="${i}" ${i == item.quantity ? 'selected' : ''}>${i}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td id="itemTotal_${item.id}"><fmt:formatNumber value="${item.totalPrice}" type="currency" currencySymbol="VND"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/cart?action=remove" method="post">
                                <input type="hidden" name="id" value="${item.id}">
                                <button type="submit">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <h3 style="text-align: right;">Tổng cộng: <span id="total-price"><fmt:formatNumber value="${total}" type="currency" currencySymbol="VND"/></span></h3>
        </c:when>
        <c:otherwise>
            <p>Giỏ hàng của bạn hiện tại không có sản phẩm nào.</p>
        </c:otherwise>
    </c:choose>

    <div style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/thuc-don" class="btn btn-secondary">Quay lại thực đơn</a>
        <a href="${pageContext.request.contextPath}/payment" class="btn btn-success">Thanh toán</a>
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
    function updateCart(id, quantity) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/cart?action=update", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.status == "success") {
                    document.getElementById("itemTotal_" + id).innerText = formatCurrency(response.updatedItem.totalPrice);
                    document.getElementById("total-price").innerText = formatCurrency(response.newTotal);
                }
            }
        };
        xhr.send("id=" + id + "&quantity=" + quantity);
    }
    function formatCurrency(amount) {
        var numberFormat = new Intl.NumberFormat('vi-VN');
        return numberFormat.format(amount) + " VND";
    }
</script>
</body>
</html>
