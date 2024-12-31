<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/partials/head.jsp" />
    <title>Khuyến mãi - FOOD</title>
    <style>
        .promotion-content {
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        .promotion-content h2 {
            font-size: 28px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }

        .promotion-content p {
            font-size: 18px;
            line-height: 1.6;
            color: #555;
        }

        .promotion-content .details {
            margin-top: 20px;
            font-size: 16px;
            color: #777;
        }

        .product-item img {
            width: 100%;
            height: auto;
            border-radius: 8px;
        }

        .product-item h3 {
            font-size: 20px;
            color: #333;
            margin-top: 15px;
        }

        .product-item p {
            font-size: 16px;
            color: #666;
            margin: 10px 0;
        }

        .product-item .price {
            font-size: 18px;
            font-weight: bold;
            color: #ff6600;
        }

        .product-item .btn-buy {
            background-color: #ff6600;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-top: 15px;
            transition: background-color 0.3s;
        }

        .product-item .btn-buy:hover {
            background-color: #ff4500;
        }
        /* CSS cho phần Blog */
        .blog-section {
            background-color: #f9f9f9;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-top: 40px;
        }

        .blog-section h2 {
            font-size: 32px;
            font-weight: bold;
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }

        .blog-post {
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .blog-post:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.2);
        }

        .blog-post h3 {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
        }

        .blog-post p {
            font-size: 16px;
            color: #666;
            line-height: 1.6;
            margin-bottom: 20px;
        }

        .read-more {
            font-size: 16px;
            font-weight: bold;
            color: #ff6600;
            text-decoration: none;
            transition: color 0.3s;
        }

        .read-more:hover {
            color: #ff4500;
        }
    </style>
</head>
<body>
<!-- Sidebar -->
<div class="collapse d-md-block sidebar" id="sidebar">
    <div>
        <div class="logo">
            <img src="img/logo.jpg" alt="logo" class="img-logo">
        </div>
        <nav>
            <a class="nav-link" href="/">TRANG CHỦ</a>
            <a class="nav-link" href="/thuc-don">THỰC ĐƠN</a>
            <a class="nav-link" href="/khuyen-mai">KHUYẾN MÃI</a>
            <a class="nav-link" href="/lien-he">LIÊN HỆ</a>
            <a class="nav-link" href="/khuyen-mai">GIỚI THIỆU</a>
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
<%-- banner--%>
    <div class="banner">
        <h1 style="text-align: left">KHUYẾN MÃI</h1>
    </div>

    <div class="promotion-content">
        <h2><i class="fas fa-gift"></i> Chương trình khuyến mãi</h2>
        <p>Chào mừng bạn đến với trang khuyến mãi của FOOD! Tại đây,
            chúng tôi cập nhật những ưu đãi hấp dẫn nhất dành cho bạn.</p>
        <div class="promotion-details">
            <h3><i class="fas fa-star"></i> Ưu đãi đặc biệt</h3>
            <p>Chúng tôi mang đến những ưu đãi đặc biệt cho các sản phẩm yêu thích của bạn. Đừng bỏ lỡ!</p>
        </div>
        <div class="promotion-details">
            <h3><i class="fas fa-snowflake"></i> Giảm giá mùa lễ hội</h3>
            <p>Hãy tham gia các chương trình giảm giá mùa lễ hội,
                với nhiều ưu đãi hấp dẫn cho bạn và gia đình.</p>
        </div>
        <div class="promotion-details">
            <h3><i class="fas fa-user-plus"></i> Chương trình khuyến mãi cho thành viên</h3>
            <p>Trở thành thành viên của chúng tôi và nhận những ưu đãi độc quyền chỉ dành cho thành viên.</p>
        </div>
    </div>

    <div class="blog-section">
        <h2><i class="fas fa-blog"></i> Blog về khuyến mãi và sản phẩm</h2>
        <div class="blog-post">
            <h3><i class="fas fa-hand-holding-usd"></i> Bài viết 1: Mẹo tiết kiệm khi mua sắm online</h3>
            <p>Trong bài viết này, chúng tôi sẽ chia sẻ các mẹo giúp bạn tiết kiệm khi mua sắm online, từ việc chọn đúng thời điểm khuyến mãi đến cách tận dụng mã giảm giá.</p>
            <a href="#" class="read-more">Đọc thêm</a>
        </div>
        <div class="blog-post">
            <h3><i class="fas fa-check-circle"></i> Bài viết 2: Tại sao nên chọn FOOD?</h3>
            <p>FOOD luôn mang đến cho bạn những sản phẩm chất lượng cao với mức giá hợp lý. Hãy cùng khám phá lý do vì sao bạn nên chọn mua tại FOOD.</p>
            <a href="#" class="read-more">Đọc thêm</a>
        </div>
        <div class="blog-post">
            <h3><i class="fas fa-calendar-day"></i> Bài viết 3: Ưu đãi đặc biệt trong tháng này</h3>
            <p>Chúng tôi mang đến những ưu đãi đặc biệt trong tháng này, bao gồm giảm giá cho sản phẩm yêu thích của bạn và nhiều hơn nữa!</p>
            <a href="#" class="read-more">Đọc thêm</a>
        </div>
    </div>

    <jsp:include page="/partials/footer.jsp" />
</div>

<div class="cart-icon">
    <i class="bi bi-cart"></i>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>