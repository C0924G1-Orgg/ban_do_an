package com.example.case_study_module_3.controller.controller;

import com.example.case_study_module_3.controller.model.CartItem;
import com.example.case_study_module_3.controller.service.ICartService;
import com.example.case_study_module_3.controller.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartController", urlPatterns = {"/cart", "/add-to-cart", "/update-cart", "/remove-from-cart"})
public class CartController extends HttpServlet {
    private final ICartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("view".equals(action)) {
            req.setAttribute("cartItems", cartService.getCartItems());
            req.setAttribute("total", cartService.calculateTotal());
            req.getRequestDispatcher("/user/cart.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "add":
                // Lấy thông tin món ăn từ form
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String image = req.getParameter("image");
                double price = Double.parseDouble(req.getParameter("price"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));

                // Thêm vào giỏ hàng
                cartService.addToCart(new CartItem(id, name, image, price, quantity));
                break;

            case "update":
                int updateId = Integer.parseInt(req.getParameter("id"));
                int updateQuantity = Integer.parseInt(req.getParameter("quantity"));
                cartService.updateQuantity(updateId, updateQuantity);
                break;

            case "remove":
                int removeId = Integer.parseInt(req.getParameter("id"));
                cartService.removeFromCart(removeId);
                break;
        }

        resp.sendRedirect("cart?action=view");
    }
}
