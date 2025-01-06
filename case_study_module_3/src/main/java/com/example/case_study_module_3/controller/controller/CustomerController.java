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
                response.sendRedirect("/view/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "update":
                updateCustomer(request, response);
                break;
            case "updateIsAdmin":
                updateIsAdmin(request, response);
                break;
            default:
                response.sendRedirect("/view/index.jsp");
        }
    }

    private void showCustomerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.getAll();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/admin/customer_list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerService.getAll().stream()
                .filter(c -> c.getCustomerId() == id)
                .findFirst()
                .orElse(null);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("/admin/edit_customer.jsp").forward(request, response);
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
