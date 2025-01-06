package com.example.case_study_module_3.controller.controller;

import com.example.case_study_module_3.controller.model.Customer;
import com.example.case_study_module_3.controller.service.ICustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = {"/customers"})
public class CustomerController extends HttpServlet {
    private ICustomerService customerService;

    @Override
    public void init() throws ServletException {
        this.customerService = (ICustomerService) getServletContext().getAttribute("customerService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "list":
                showCustomerList(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "register":
                registerCustomer(request, response);
                break;
            case "login":
                loginCustomer(request, response);
                break;
            case "update":
                updateCustomer(request, response);
                break;
            case "updateIsAdmin":
                updateIsAdmin(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void showCustomerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.getAll();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/customer_list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerService.getAll().stream()
                .filter(c -> c.getCustomerId() == id)
                .findFirst()
                .orElse(null);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("/edit_customer.jsp").forward(request, response);
    }

    private void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

        Customer newCustomer = new Customer(0, name, email, password, phone, address, isAdmin);
        boolean success = customerService.register(newCustomer);

        if (success) {
            response.sendRedirect("customers?action=list");
        } else {
            response.sendRedirect("register.jsp?error=Email already exists");
        }
    }

    private void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Customer customer = customerService.login(email, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInCustomer", customer);
            session.setMaxInactiveInterval(30 * 60); // 30 phút

            if ("on".equals(remember)) {
                Cookie loginCookie = new Cookie("user", customer.getCustomerEmail());
                loginCookie.setMaxAge(30 * 24 * 60 * 60); // 30 ngày
                response.addCookie(loginCookie);
            }

            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

        Customer updatedCustomer = new Customer(id, name, email, password, phone, address, isAdmin);
        boolean success = customerService.update(updatedCustomer);

        if (success) {
            response.sendRedirect("customers?action=list");
        } else {
            response.sendRedirect("edit_customer.jsp?error=Update failed");
        }
    }

    private void updateIsAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer loggedInCustomer = (Customer) request.getSession().getAttribute("loggedInCustomer");
        if (loggedInCustomer == null || !loggedInCustomer.isAdmin()) {
            response.sendRedirect("login.jsp?error=Unauthorized");
            return;
        }
    }
}
