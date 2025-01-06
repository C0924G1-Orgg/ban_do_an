<%@ page contentType="text/html;charset=UTF-8" %>
    <!-- Sidebar -->
    <div class="collapse d-md-block sidebar" id="sidebar">
        <div>
            <!-- Logo -->
            <div class="logo">
                <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="logo" class="img-logo">
            </div>
            <!-- Navigation -->
            <nav>
                <a class="nav-link" href="#">TRANG CHỦ</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/product_list.jsp">THỰC ĐƠN</a>
                <a class="nav-link" href="#">KHUYẾN MÃI</a>
                <a class="nav-link" href="#">LIÊN HỆ</a>
                <a class="nav-link" href="#">GIỚI THIỆU</a>
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
