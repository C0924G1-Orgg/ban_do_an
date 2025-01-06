<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh Toán - Food</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <jsp:include page="/partials/head.jsp"></jsp:include>
    <style>
        .banner{
            height: 150px;
        }
        form {
            max-width: 800px;
            margin: 20px auto; /* Căn giữa form trên trang */
            margin-left: 300px; /* Căn giữa form trên trang */
            margin-right: 300px ; /* Căn giữa form trên trang */
            padding: 20px;
            border: 1px solid #ddd; /* Thêm viền cho form */
            border-radius: 8px; /* Làm tròn các góc viền */
            background-color: #f9f9f9; /* Màu nền sáng cho form */
            display: flex;
            flex-direction: column; /* Sắp xếp theo cột */
            gap: 15px; /* Khoảng cách giữa các phần tử */
        }
        .form-group {
            display: flex; /* Sử dụng flex để căn chỉnh label và input ngang hàng */
            justify-content: space-between; /* Căn chỉnh label và input đều */
            align-items: center; /* Căn chỉnh theo chiều dọc */
        }
        label {
            width: 30%; /* Đặt chiều rộng cho label */
            font-weight: bold; /* Làm đậm label */
            margin-bottom: 0; /* Loại bỏ khoảng cách dưới label */
        }

        input[type="text"] {
            width: 65%; /* Đặt chiều rộng cho input */
            padding: 10px;
            border: 1px solid #ccc; /* Viền của input */
            border-radius: 4px; /* Làm tròn góc của input */
            font-size: 16px; /* Tăng kích thước chữ cho dễ đọc */
        }
        input[type="text"]:focus {
            border-color: #4CAF50; /* Thay đổi màu viền khi focus */
            outline: none; /* Loại bỏ viền mặc định */
        }
        .form-buttons {
            display: flex; /* Hiển thị các nút ngang nhau */
            gap: 15px; /* Khoảng cách giữa các nút */
            width: 100%; /* Đảm bảo container chiếm hết chiều rộng */
        }
        button, a.btn {
            padding: 10px 20px;
            font-size: 16px;
            text-align: center;
            border-radius: 4px;
            text-decoration: none; /* Loại bỏ gạch chân từ các link */
            cursor: pointer;
            width: 100%; /* Đảm bảo nút chiếm hết chiều rộng trong container */
        }
        button.btn-success {
            background-color: #28a745; /* Màu nền cho nút Xác nhận thanh toán */
            border: none;
            color: white; /* Màu chữ trắng */
        }
        a.btn-secondary {
            background-color: #6c757d; /* Màu nền cho nút Huỷ */
            border: none;
            color: white; /* Màu chữ trắng */
        }
        button:hover, a.btn:hover {
            opacity: 0.9; /* Hiệu ứng hover khi di chuột vào nút */
        }

    </style>
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
    <div class="banner">
        <h1>THANH TOÁN</h1>
    </div>
    <c:choose>
        <c:when test="${not empty cartItems}">
            <h3 style="text-align: center; padding-top: 10px">Thông tin đơn hàng:</h3>
            <table border="1" cellpadding="10" cellspacing="0" style="width: 100%; text-align: center;">
                <thead>
                <tr>
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Tổng</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td><img src="${item.image}" alt="${item.name}" width="100"></td>
                        <td>${item.name}</td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="VND"/></td>
                        <td><fmt:formatNumber value="${item.totalPrice}" type="currency" currencySymbol="VND"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <h3 style="text-align: center;">Tổng cộng:
                <span id="total-price" style="color: #a62c06">
                    <fmt:formatNumber value="${total}" type="currency" currencySymbol="VND"/>
                </span>
            </h3>
            <hr>

            <h3 style="text-align: center">Thông tin khách hàng:</h3>
            <form action="${pageContext.request.contextPath}/payment" method="post" style="border: 1px">
                <div class="form-group">
                    <label for="name">Họ và tên:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại:</label>
                    <input type="text" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="address">Địa chỉ:</label>
                    <input type="text" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" required>
                </div>
                <!-- Trường ẩn chứa tổng tiền -->
                <input type="hidden" name="total" value="${total}">

                <!-- Container cho các nút -->
                <div class="form-buttons">
                    <button type="submit" class="btn btn-success">Xác nhận thanh toán</button>
                    <a href="${pageContext.request.contextPath}/cart?action=view" class="btn btn-secondary">Huỷ</a>
                </div>
            </form>

        </c:when>
        <c:otherwise>
            <div style="text-align: center; margin-top: auto">
            <h3>Giỏ hàng của bạn hiện tại không có sản phẩm nào.</h3>
            <a href="${pageContext.request.contextPath}/thuc-don" class="btn btn-secondary">Quay lại thực đơn</a>

            </div>
        </c:otherwise>
    </c:choose>
    <jsp:include page="/partials/footer.jsp" />

</div>
</body>
</html>
