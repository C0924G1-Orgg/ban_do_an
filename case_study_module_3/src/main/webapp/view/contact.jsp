<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="/partials/head.jsp" />
  <title>Liên hệ - FOOD</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
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
      <a class="nav-link" href="/gioi-thieu">GIỚI THIỆU</a>
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
  <div class="banner">
    <h1 style="text-align: left">LIÊN HỆ VỚI FOOD</h1>
  </div>

  <div class="contact-content">
    <h2><i class="fas fa-phone-alt"></i> Liên hệ với chúng tôi</h2>
    <p>Nếu bạn có bất kỳ câu hỏi hoặc góp ý nào, xin vui lòng liên hệ với chúng tôi qua các kênh sau:</p>
    <ul>
      <li><i class="fas fa-envelope"></i> Email: support@food.com</li>
      <li><i class="fas fa-phone"></i> Điện thoại: 0123 456 789</li>
      <li><i class="fas fa-map-marker-alt"></i> Địa chỉ: 123 Đường ABC, Quận 1, TP.HCM</li>
    </ul>
    <!-- Contact Form -->
    <div class="contact-form">
      <h3><i class="fas fa-pencil-alt"></i> Gửi tin nhắn cho chúng tôi</h3>
      <form action="#" method="post">
        <div class="form-group">
          <label for="name">Tên của bạn:</label>
          <input type="text" id="name" name="name" class="form-control" required />
        </div>
        <div class="form-group">
          <label for="email">Email của bạn:</label>
          <input type="email" id="email" name="email" class="form-control" required />
        </div>
        <div class="form-group">
          <label for="message">Lời nhắn:</label>
          <textarea id="message" name="message" class="form-control" rows="5" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Gửi</button>
      </form>
    </div>
    <!-- Google Map -->
    <div class="google-map">
      <h3><i class="fas fa-map-marked-alt"></i> Tìm chúng tôi trên bản đồ</h3>
      <iframe src="https://www.google.com/maps/embed/v1/place?key=YOUR_API_KEY&q=123+Đường+ABC,+Quận+1,+TP.HCM"
              width="100%" height="300" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
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
