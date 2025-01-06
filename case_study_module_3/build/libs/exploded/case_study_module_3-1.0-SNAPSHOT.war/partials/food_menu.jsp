<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="food-menu">
    <div class="container">
        <h2>Món Ăn Nổi Bật</h2>
        <div class="row">
            <c:forEach var="food" items="${foods}" varStatus="status">
                <c:if test="${status.index < 3}"> <!-- Hiển thị chỉ 3 món ăn đầu tiên -->
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <img src="${food.foodImage}" class="card-img-top" alt="${food.foodName}">
                            <div class="card-body">
                                <h5 class="card-title">${food.foodName}</h5>
                                <p class="card-text">${food.foodDescription}</p>
                                <p class="card-text">Giá:
                                    <fmt:formatNumber value="${food.foodPrice}" type="currency" currencySymbol="VND"/>
                                </p>
                                <button class="btn btn-primary w-100">Thêm</button>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="img/ba_chi_cuon_nam.jpg" class="card-img-top" alt="Food 1">
                    <div class="card-body">
                        <h5 class="card-title">Ba chỉ cuộn nấm</h5>
                        <p class="card-text">Món ăn hấp dẫn với gà và lá dứa thơm lừng.</p>
                        <p class="card-text">Giá: 30,000 VND</p>
                        <button class="btn btn-primary w-100">Thêm</button>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="img/nam_nuong_ong_tre.png" class="card-img-top" alt="Food 2">
                    <div class="card-body">
                        <h5 class="card-title">Nấm nướng ống tre</h5>
                        <p class="card-text">Cơm mềm mịn với đùi gà chiên giòn.</p>
                        <p class="card-text">Giá: 50,000 VND</p>
                        <button class="btn btn-primary w-100">Thêm</button>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="img/nam_nuong_ong_tre.png" class="card-img-top" alt="Food 2">
                    <div class="card-body">
                        <h5 class="card-title">Nấm nướng ống tre</h5>
                        <p class="card-text">Cơm mềm mịn với đùi gà chiên giòn.</p>
                        <p class="card-text"> 50.000₫</p>
                        <button class="btn btn-primary w-100">Thêm</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
