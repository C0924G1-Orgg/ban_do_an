<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/partials/head.jsp"></jsp:include>
    <style>
        .banner {
            background: url('../img/banner.jpg') no-repeat center center / cover;
            height: 550px;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
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

    <!-- Main Content -->
    <div class="main-content">
      <jsp:include page="/partials/banner.jsp" />
      <jsp:include page="/partials/food_menu.jsp" />
      <jsp:include page="/partials/feedback_form.jsp" />
      <jsp:include page="/partials/utilities.jsp" />
      <jsp:include page="/partials/footer.jsp" />
    </div>

    <div class="cart-icon">
        <i class="bi bi-cart"></i>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
