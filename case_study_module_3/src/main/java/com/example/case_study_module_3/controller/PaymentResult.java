package com.example.case_study_module_3.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "PaymentResult", urlPatterns = {"/payment-success"})
public class PaymentResult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        String vnp_HashSecret = "U6VCMLUN8NG7YCSXFL74L7OWEJM60PVV";

        String vnp_SecureHash = req.getParameter("PCLJDQ21");
        StringBuilder data = new StringBuilder();

        params.forEach((key, values) -> {
            if (!key.equals("PCLJDQ21")) {
                data.append(key).append('=').append(values[0]).append('&');
            }
        });

        data.setLength(data.length() - 1);

        try {
            String computedHash = VNPayService.hmacSHA512(vnp_HashSecret, data.toString());
            if (computedHash.equals(vnp_SecureHash)) {
                req.setAttribute("message", "Thanh toán thành công!");
            } else {
                req.setAttribute("message", "Giao dịch không hợp lệ!");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Lỗi xử lý giao dịch!");
        }

        req.getRequestDispatcher("/user/payment-success.jsp").forward(req, resp);
    }
}
