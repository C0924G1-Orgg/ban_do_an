package com.example.case_study_module_3.controller.controller;


import com.example.case_study_module_3.controller.model.Customer;
import com.example.case_study_module_3.controller.service.ICustomerService;
import com.example.case_study_module_3.controller.service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UserCustomerController", urlPatterns = "/user/customers")
public class UserCustomerController extends HttpServlet {

    private ICustomerService customerService;

    @Override
    public void init() throws ServletException {
        this.customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "logout":
                logoutCustomer(request, response);
                break;
            default:
                response.sendRedirect("/user/login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                loginCustomer(request, response);
                break;
            case "register":
                registerCustomer(request, response);
                break;
            case "update":
                updateCustomer(request, response);
                break;
            default:
                response.sendRedirect("/user/login.jsp");
                break;
        }
    }

    private void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean remember = "on".equals(request.getParameter("remember"));

        Customer customer = customerService.login(email, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInCustomer", customer);

            if (remember) {
                Cookie emailCookie = new Cookie("email", customer.getCustomerEmail());
                Cookie passwordCookie = new Cookie("password", customer.getCustomerPassword());
                emailCookie.setMaxAge(60 * 60 * 24 * 7);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(emailCookie);
                response.addCookie(passwordCookie);
            }

            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }

    private void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        if (customerService.findByEmail(email) != null) {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(0, name, email, password, phone, address);
        customerService.save(customer);

        response.sendRedirect("/user/login.jsp");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

        if (loggedInCustomer == null) {
            response.sendRedirect("/user/login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        loggedInCustomer.setCustomerName(name);
        loggedInCustomer.setCustomerPhone(phone);
        loggedInCustomer.setCustomerAddress(address);

        customerService.update(loggedInCustomer);

        response.sendRedirect("/user/dashboard.jsp");
    }

    private void logoutCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        Cookie emailCookie = new Cookie("email", null);
        emailCookie.setMaxAge(0);
        Cookie passwordCookie = new Cookie("password", null);
        passwordCookie.setMaxAge(0);
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        response.sendRedirect("/user/login.jsp");
    }
}
