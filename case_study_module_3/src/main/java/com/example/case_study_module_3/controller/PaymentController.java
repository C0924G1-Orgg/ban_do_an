package com.example.case_study_module_3.controller;

import com.example.case_study_module_3.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "PaymentController", urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    private static final String VNP_TMNCODE = "PCLJDQ21";
    private static final String VNP_HASHSECRET = "U6VCMLUN8NG7YCSXFL74L7OWEJM60PVV";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<CartItem> cartItems = getCartItemsFromCookies(req);
            double total = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();

            req.setAttribute("cartItems", cartItems);
            req.setAttribute("total", total);

            req.getRequestDispatcher("/user/payment.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Lỗi khi tải trang thanh toán. Vui lòng thử lại!");
            req.getRequestDispatcher("/user/payment.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            double total = Double.parseDouble(req.getParameter("total"));
//đoạn code dưới giúp trả về trang localhost của mình khi thanh ton VNPay thành công
            String returnUrl = "http://localhost:8080/payment-success";
            String orderInfo = "Thanh toán đơn hàng tại FoodStore";
            String orderId = UUID.randomUUID().toString();

            String paymentUrl = VNPayService.generatePaymentUrl(returnUrl, total, orderInfo, orderId);
            clearCartCookies(req, resp);
            resp.sendRedirect(paymentUrl); // Điều hướng đến URL thanh toán của VNPay
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình xử lý thanh toán!");
            req.getRequestDispatcher("/user/payment.jsp").forward(req, resp);
        }
    }


    private List<CartItem> getCartItemsFromCookies(HttpServletRequest req) {
        try {
            return new CartController().getCartItemsFromCookies(req);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể lấy giỏ hàng từ cookies");
        }
    }

    private void clearCartCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("cartItem_")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }
    }
}
