<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="/partials/head.jsp"></jsp:include>
  <title>Thanh Toán Thành Công</title>
  <style>
    .success-message {
      text-align: center;
      margin-top: 50px;
    }

    .success-message h1 {
      color: #28a745;
    }

    .success-message p {
      margin: 15px 0;
    }

    .success-message .btn {
      margin-top: 20px;
    }
  </style>
</head>
<body>
<!-- Sidebar -->
<jsp:include page="/partials/sidebar.jsp" />

<!-- Main Content -->
<div class="main-content">
  <!-- Banner -->
  <jsp:include page="/partials/banner.jsp" />

  <!-- Payment Success Message -->
  <div class="success-message " style="color: black">
    <h1>Cảm ơn bạn đã mua hàng!</h1>
    <p>Đơn hàng của bạn đã được ghi nhận và đang được xử lý.</p>
    <p>Bạn sẽ sớm nhận được thông báo giao hàng.</p>
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary" style="background-color:#772704 ">Quay lại trang chủ</a>
  </div>

  <!-- Footer -->
  <jsp:include page="/partials/footer.jsp" />
</div>

<!-- Cart Icon -->
<div class="cart-icon">
  <a href="${pageContext.request.contextPath}/cart?action=view">
    <i class="bi bi-cart"></i>
    <span class="badge bg-danger">${cartSize}</span>
  </a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
