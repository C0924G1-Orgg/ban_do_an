package com.example.case_study_module_3.controller;

import com.example.case_study_module_3.model.Customer;
import com.example.case_study_module_3.service.ICustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private ICustomerService customerService;

    @Override
    public void init() throws ServletException {
        this.customerService = (ICustomerService) getServletContext().getAttribute("customerService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("customer_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        boolean isAdmin = false;

        Customer newCustomer = new Customer(0, name, email, password, phone, address, isAdmin);
        boolean success = customerService.register(newCustomer);

        if (success) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", newCustomer);
            Cookie emailCookie = new Cookie("email", email);
            emailCookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(emailCookie);
            response.sendRedirect("/login");
        } else {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
        }
    }
}
