<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/24/2024
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="banner2">
    <h1>Góp Ý</h1>
    <form class="form-feedback">
        <div class="row">
            <input type="text" name="name" placeholder="Tên" class="input-half" required>
            <input type="text" name="phone" placeholder="Số điện thoại" class="input-half" required>
        </div>
        <div class="row">
            <input type="text" name="address" placeholder="Địa chỉ" class="input-half" required>
            <input type="email" name="email" placeholder="Email" class="input-half" required>
        </div>
        <textarea name="content" placeholder="Nội dung" required></textarea>
        <button type="submit" class="submit-btn">Gửi cho chúng tôi</button>
    </form>
</div>
