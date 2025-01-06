package com.example.case_study_module_3.controller.controller;

import com.example.case_study_module_3.controller.model.Customer;
import com.example.case_study_module_3.controller.service.ICustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = {"/customers"})
public class CustomerController extends HttpServlet {
    private ICustomerService customerService;

    @Override
    public void init() throws ServletException {
        // Khởi tạo CustomerService với Repository (cần chỉnh sửa để phù hợp với ứng dụng của bạn)
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

        Customer newCustomer = new Customer(0, name, email, password, phone, address);
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

        Customer customer = customerService.login(email, password);

        if (customer != null) {
            request.getSession().setAttribute("loggedInCustomer", customer);
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

        Customer updatedCustomer = new Customer(id, name, email, password, phone, address);
        boolean success = customerService.update(updatedCustomer);

        if (success) {
            response.sendRedirect("customers?action=list");
        } else {
            response.sendRedirect("edit_customer.jsp?error=Update failed");
        }
    }
}
