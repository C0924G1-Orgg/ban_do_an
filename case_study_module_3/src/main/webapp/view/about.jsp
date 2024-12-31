<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Giới thiệu - FOOD</title>
  <jsp:include page="/partials/head.jsp" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
</head>
<body>
<!-- Sidebar -->
<div class="collapse d-md-block sidebar" id="sidebar">
  <div>
    <!-- Logo -->
    <div class="logo">
      <img src="img/logo.jpg" alt="logo" class="img-logo">
    </div>
    <!-- Navigation -->
    <nav>
      <a class="nav-link" href="/">TRANG CHỦ</a>
      <a class="nav-link" href="/thuc-don">THỰC ĐƠN</a>
      <a class="nav-link" href="/khuyen-mai">KHUYẾN MÃI</a>
      <a class="nav-link" href="/lien-he">LIÊN HỆ</a>
      <a class="nav-link" href="/gioi-thieu">GIỚI THIỆU</a>
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
    <h1 style="text-align: left">GIỚI THIỆU </h1>
  </div>

  <!-- About Us Content -->
  <div class="about-content">
    <h2><i class="fas fa-info-circle"></i> Về chúng tôi</h2>
    <p>FOOD là nền tảng đặt món ăn trực tuyến hàng đầu, mang đến cho bạn trải nghiệm ẩm thực đa dạng và tiện lợi.</p>
    <p>Chúng tôi cam kết cung cấp những món ăn chất lượng từ các nhà hàng uy tín, đảm bảo sự hài lòng cho khách hàng.</p>

    <!-- Mission and Vision -->
    <div class="mission-vision">
      <h3><i class="fas fa-bullseye"></i> Sứ mệnh</h3>
      <p>Chúng tôi mang đến cho khách hàng sự tiện lợi, chất lượng và sự phong phú trong lựa chọn món ăn, giúp bạn có những bữa ăn ngon miệng mọi lúc mọi nơi.</p>

      <h3><i class="fas fa-eye"></i> Tầm nhìn</h3>
      <p>FOOD hướng đến trở thành nền tảng đặt món ăn trực tuyến được yêu thích nhất tại Việt Nam, mang lại giá trị cho khách hàng, đối tác và cộng đồng.</p>
    </div>

    <!-- Team Section -->
    <div class="team-section">
      <h3><i class="fas fa-users"></i> Đội ngũ của chúng tôi</h3>
      <p>FOOD tự hào có một đội ngũ chuyên nghiệp, nhiệt huyết và đầy sáng tạo, luôn sẵn sàng phục vụ khách hàng mọi lúc mọi nơi.</p>
      <div class="team-members">
        <div class="team-member">
          <img src="img/team1.jpg" alt="Team Member 1" class="team-img" />
          <h4>Nguyễn Văn A</h4>
          <p>CEO & Nhà sáng lập</p>
        </div>
        <div class="team-member">
          <img src="img/team2.jpg" alt="Team Member 2" class="team-img" />
          <h4>Trần Thị B</h4>
          <p>Giám đốc điều hành</p>
        </div>
        <div class="team-member">
          <img src="img/team3.jpg" alt="Team Member 3" class="team-img" />
          <h4>Phan Văn C</h4>
          <p>Giám đốc công nghệ</p>
        </div>
      </div>
    </div>

    <!-- History Section -->
    <div class="history-section">
      <h3><i class="fas fa-history"></i> Lịch sử hình thành</h3>
      <p>FOOD được thành lập vào năm 2015 với mục tiêu kết nối khách hàng với những nhà hàng uy tín, cung cấp các dịch vụ đặt món ăn nhanh chóng và tiện lợi.</p>
      <p>Chúng tôi đã từng bước xây dựng thương hiệu và ngày càng phát triển mạnh mẽ, trở thành một trong những nền tảng đặt món ăn online lớn tại Việt Nam.</p>
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
