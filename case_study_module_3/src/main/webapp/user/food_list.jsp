<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/partials/head.jsp"/>
<body>
<!-- Sidebar -->
<div class="collapse d-md-block sidebar" id="sidebar">
    <div>
        <!-- Logo -->
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="logo" class="img-logo">
        </div>
        <!-- Navigation -->
        <nav>
            <a class="nav-link" href="${pageContext.request.contextPath}/">TRANG CHỦ</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/thuc-don">THỰC ĐƠN</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/khuyen-mai">KHUYẾN MÃI</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/lien-he">LIÊN HỆ</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/gioi-thieu">GIỚI THIỆU</a>
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
        <h1 style="font-size: 30px">THỰC ĐƠN HÔM NAY</h1>
    </div>
    <div class="food-menu">
        <div class="container">
            <h2>THỰC ĐƠN</h2>
            <c:choose>
                <c:when test="${empty foods}">
                    <p class="text-center">Hiện chưa có món ăn nào trong thực đơn.</p>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <c:forEach var="food" items="${foods}">
                            <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                                <div class="card">
                                    <img src="${food.image}" class="card-img-top" alt="${food.foodName}">
                                    <div class="card-body">
                                        <h5 class="card-title">${food.foodName}</h5>
                                        <p class="card-text" style="color: #a62c06; font-weight: bold">Giá:
                                            <fmt:formatNumber value="${food.foodPrice}" type="currency" currencySymbol="VND"/>
                                        </p>
                                        <div class="d-flex justify-content-between align-items-center mt-2">
                                            <!-- Nút Thêm vào Giỏ hàng -->
                                            <form action="${pageContext.request.contextPath}/cart?action=add" method="post" class="me-1 w-50">
                                                <input type="hidden" name="id" value="${food.foodId}">
                                                <input type="hidden" name="name" value="${food.foodName}">
                                                <input type="hidden" name="image" value="${food.image}">
                                                <input type="hidden" name="price" value="${food.foodPrice}">
                                                <input type="hidden" name="quantity" value="1"> <!-- Mặc định số lượng là 1 -->
                                                <button type="submit" class="btn btn-primary d-flex justify-content-center align-items-center w-100 icon-button mt-2">
                                                    <i class="fas fa-cart-plus me-2 "></i>
                                                </button>
                                            </form>

                                            <!-- Nút Xem Chi tiết -->
                                            <!-- Nút Xem Chi tiết -->
                                            <a href="javascript:void(0)"
                                               class="btn btn-secondary w-50 mt-2 icon-button"
                                               data-id="${food.foodId}"
                                               data-name="${food.foodName}"
                                               data-description="${food.foodDescription}"
                                               data-price="${food.foodPrice}"
                                               data-image="${food.image}"
                                               onclick="fetchProductDetails(this)">
                                                <i class="fas fa-eye me-2"></i>
                                            </a>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Cart Icon -->
    <div class="cart-icon">
        <a href="${pageContext.request.contextPath}/cart?action=view">
            <i class="bi bi-cart"></i>
            <span class="badge bg-danger">${cartSize}</span>
        </a>
    </div>
    <jsp:include page="/partials/footer.jsp" />
</div>

<!-- Modal -->
<div id="product-modal" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-product-name">Chi tiết sản phẩm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <img id="modal-product-image" class="img-fluid mb-3" alt="Product Image">
                <p id="modal-product-description"></p>
                <p  style="color: #a62c06; font-weight: bold"><strong>Giá:</strong> <span id="modal-product-price"></span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>


<script>
    function fetchProductDetails(button) {
        // Lấy thông tin từ các thuộc tính data-* của button
        const productId = button.getAttribute('data-id');
        const productName = button.getAttribute('data-name');
        const productDescription = button.getAttribute('data-description');
        const productPrice = button.getAttribute('data-price');
        const productImage = button.getAttribute('data-image');

        // Cập nhật nội dung của modal
        document.getElementById('modal-product-name').textContent = productName;
        document.getElementById('modal-product-description').textContent = productDescription;
        document.getElementById('modal-product-price').textContent = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(productPrice);
        document.getElementById('modal-product-image').src = productImage;

        // Hiển thị modal
        const productModal = new bootstrap.Modal(document.getElementById('product-modal'));
        productModal.show();
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
