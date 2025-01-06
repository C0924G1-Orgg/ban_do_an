package com.example.case_study_module_3.controller;

import com.example.case_study_module_3.dto.OrderDTO;
import com.example.case_study_module_3.repository.OrderRepository;
import com.example.case_study_module_3.service.IOrderService;
import com.example.case_study_module_3.service.impl.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "orderController", urlPatterns = "/order")
public class OrderController extends HttpServlet {

    private static final String ACTION = "action";
    private static final String MESSAGE = "message";

    private static final String CREATE_SUCCESS = "Order created successfully.";
    private static final String UPDATE_SUCCESS = "Order updated successfully.";
    private static final String DELETE_SUCCESS = "Order deleted successfully.";
    private static final String NOT_FOUND = "Order not found.";

    private final IOrderService orderService = new OrderService(new OrderRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter(ACTION);
        if (action == null) action = "";
        switch (action) {
            case "create":
                handleCreatePage(req, resp);
                break;
            case "update":
                handleUpdatePage(req, resp);
                break;
            case "delete":
                handleDelete(req, resp);
                break;
            default:
                handleList(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter(ACTION);
        if (action == null) action = "";
        switch (action) {
            case "create":
                handleCreate(req, resp);
                break;
            case "update":
                handleUpdate(req, resp);
                break;
        }
    }

    private void handleCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/order_create.jsp").forward(req, resp);
    }

    private void handleUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            OrderDTO order = orderService.findById(id);
            if (order != null) {
                req.setAttribute("order", order);
                req.getRequestDispatcher("/admin/order_update.jsp").forward(req, resp);
            } else {
                redirectWithMessage(resp, NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            redirectWithMessage(resp, NOT_FOUND);
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            if (orderService.deleteById(id)) {
                redirectWithMessage(resp, DELETE_SUCCESS);
            } else {
                redirectWithMessage(resp, NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            redirectWithMessage(resp, NOT_FOUND);
        }
    }

    private void handleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter(MESSAGE);
        if (message != null) {
            req.setAttribute("message", message);
        }
        List<OrderDTO> orders = orderService.getAllDTO();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/admin/order_list.jsp").forward(req, resp);
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(req.getParameter("customerId"));
            int foodId = Integer.parseInt(req.getParameter("foodId"));
            int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
            String orderDate = req.getParameter("orderDate");
            double totalOrderPrice = Double.parseDouble(req.getParameter("totalOrderPrice"));
            String orderStatus = req.getParameter("orderStatus");

            OrderDTO newOrder = new OrderDTO(0, customerId, foodId, restaurantId, orderDate, totalOrderPrice, orderStatus);
            orderService.save(newOrder);
            redirectWithMessage(resp, CREATE_SUCCESS);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input. Please check your data.");
            req.getRequestDispatcher("/admin/order_create.jsp").forward(req, resp);
        }
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            int customerId = Integer.parseInt(req.getParameter("customerId"));
            int foodId = Integer.parseInt(req.getParameter("foodId"));
            int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
            String orderDate = req.getParameter("orderDate");
            double totalOrderPrice = Double.parseDouble(req.getParameter("totalOrderPrice"));
            String orderStatus = req.getParameter("orderStatus");

            OrderDTO updatedOrder = new OrderDTO(orderId, customerId, foodId, restaurantId, orderDate, totalOrderPrice, orderStatus);
            if (orderService.update(updatedOrder)) {
                redirectWithMessage(resp, UPDATE_SUCCESS);
            } else {
                redirectWithMessage(resp, NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input. Please check your data.");
            req.getRequestDispatcher("/admin/order_update.jsp").forward(req, resp);
        }
    }

    private void redirectWithMessage(HttpServletResponse resp, String message) throws IOException {
        resp.sendRedirect("/order?" + MESSAGE + "=" + message);
    }
}
