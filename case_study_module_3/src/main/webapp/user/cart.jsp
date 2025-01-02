<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Giỏ Hàng - Food</title>
    <jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
<%--sidebar--%>
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
    <jsp:include page="/partials/banner.jsp" />

    <%-- Giỏ hàng --%>
    <h1>Giỏ Hàng</h1>
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
                <td>${item.price}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/cart?action=update" method="post">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="number" name="quantity" value="${item.quantity}" min="1" style="width: 50px;">
                        <button type="submit">Cập nhật</button>
                    </form>
                </td>
                <td>${item.totalPrice}</td>
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

    <h3 style="text-align: right;">Tổng cộng: ${total}</h3>

    <jsp:include page="/partials/footer.jsp" />
</div>

<div class="cart-icon">
    <i class="bi bi-cart"></i>
</div>

</body>
</html>
