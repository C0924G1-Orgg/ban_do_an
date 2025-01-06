package com.example.case_study_module_3.controller;

import com.example.case_study_module_3.model.Customer;
import com.example.case_study_module_3.service.ICustomerService;
import com.example.case_study_module_3.service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private ICustomerService customerService;

    @Override
    public void init() throws ServletException {
        // Lấy customerService từ context hoặc tạo mới nếu cần
        this.customerService = (ICustomerService) getServletContext().getAttribute("customerService");
        if (this.customerService == null) {
            this.customerService = new CustomerService(); // Khởi tạo dịch vụ nếu chưa có
            getServletContext().setAttribute("customerService", this.customerService);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra cookie "user" để tự động điền email
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    request.setAttribute("savedEmail", cookie.getValue());
                    break;
                }
            }
        }
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");


        Customer customer = customerService.login(email, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInCustomer", customer);
            session.setMaxInactiveInterval(30 * 60); // Phiên tồn tại trong 30 phút

            if ("on".equals(remember)) {
                Cookie loginCookie = new Cookie("user", customer.getCustomerEmail());
                loginCookie.setMaxAge(30 * 24 * 60 * 60); // Cookie tồn tại trong 30 ngày
                loginCookie.setHttpOnly(true); // Bảo vệ khỏi truy cập JavaScript
                loginCookie.setSecure(request.isSecure()); // Chỉ gửi cookie qua HTTPS nếu có
                response.addCookie(loginCookie);
            }
            response.sendRedirect(request.getContextPath() + "/user/dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }
}
