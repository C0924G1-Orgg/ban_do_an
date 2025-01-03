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
        </div>
    </div>
</div>
