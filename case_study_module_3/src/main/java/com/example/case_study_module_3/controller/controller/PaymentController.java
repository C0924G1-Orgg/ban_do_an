package com.example.case_study_module_3.controller.controller;

import com.example.case_study_module_3.controller.model.CartItem;
import com.example.case_study_module_3.controller.repository.BaseRepository;
import com.example.case_study_module_3.controller.repository.OrderRepository;
import com.example.case_study_module_3.controller.service.impl.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
    private PaymentService paymentService;

    @Override
    public void init() throws ServletException {
        Connection connection = BaseRepository.getConnection();
        OrderRepository orderRepository = new OrderRepository(connection);
        paymentService = new PaymentService(orderRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cartItems");
        if (cartItems != null) {
            double totalAmount = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("totalAmount", totalAmount);
        }
        request.getRequestDispatcher("/user/payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cartItems");
        if (cartItems != null) {
            double totalAmount = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
            boolean paymentProcessed = paymentService.processPayment(cartItems, totalAmount);

            if (paymentProcessed) {
                response.sendRedirect(request.getContextPath() + "/payment-success.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/payment-error.jsp");
            }
        }
    }
}
