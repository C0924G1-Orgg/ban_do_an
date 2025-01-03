package com.example.case_study_module_3.controller.controller;

import com.example.case_study_module_3.controller.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartController", urlPatterns = {"/cart", "/add-to-cart", "/update-cart", "/remove-from-cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        resp.setCharacterEncoding("UTF-8");

        if ("view".equals(action)) {
            List<CartItem> cartItems = getCartItemsFromCookies(req);
            double total = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
            req.setAttribute("cartItems", cartItems);
            req.setAttribute("total", total);
            req.getRequestDispatcher("/user/cart.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        resp.setCharacterEncoding("UTF-8");

        switch (action) {
            case "add":
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String image = req.getParameter("image");
                double price = Double.parseDouble(req.getParameter("price"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                CartItem newItem = new CartItem(id, name, image, price, quantity);
                addCartItemToCookies(newItem, req, resp);
                break;
            case "update":
                int updateId = Integer.parseInt(req.getParameter("id"));
                int updateQuantity = Integer.parseInt(req.getParameter("quantity"));
                updateCartItemInCookies(updateId, updateQuantity, req, resp);
                return;
            case "remove":
                int removeId = Integer.parseInt(req.getParameter("id"));
                removeCartItemFromCookies(removeId, req, resp);
                break;
        }

        resp.sendRedirect("cart?action=view");
    }

    private List<CartItem> getCartItemsFromCookies(HttpServletRequest req) {
        List<CartItem> cartItems = new ArrayList<>();
        javax.servlet.http.Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().startsWith("cartItem_")) {
                    try {
                        String decodedValue = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.toString());

                        String[] parts = decodedValue.split(";");
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String image = parts[2];
                        double price = Double.parseDouble(parts[3]);
                        int quantity = Integer.parseInt(parts[4]);

                        CartItem cartItem = new CartItem(id, name, image, price, quantity);
                        cartItems.add(cartItem);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cartItems;
    }

    private void addCartItemToCookies(CartItem cartItem, HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Tạo giá trị cookie từ thuộc tính của cartItem
            String value = cartItem.getId() + ";" + cartItem.getName() + ";" + cartItem.getImage() + ";"
                    + cartItem.getPrice() + ";" + cartItem.getQuantity();
            String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("cartItem_" + cartItem.getId(), encodedValue);
            cookie.setMaxAge(60 * 60 * 24);

            cookie.setPath("/");

            resp.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCartItemInCookies(int id, int quantity, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        javax.servlet.http.Cookie[] cookies = req.getCookies();
        double itemTotalPrice = 0;
        double cartTotal = 0;

        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals("cartItem_" + id)) {
                    try {
                        String decodedValue = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.toString());
                        String[] parts = decodedValue.split(";");

                        double price = Double.parseDouble(parts[3]);
                        itemTotalPrice = price * quantity;

                        String newValue = parts[0] + ";" + parts[1] + ";" + parts[2] + ";" + parts[3] + ";" + quantity;
                        cookie.setValue(URLEncoder.encode(newValue, StandardCharsets.UTF_8.toString()));
                        resp.addCookie(cookie);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

            List<CartItem> cartItems = getCartItemsFromCookies(req);
            cartTotal = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
        }

        resp.setContentType("application/json");
        resp.getWriter().write("{\"status\":\"success\",\"updatedItem\":{\"id\":" + id +
                ",\"totalPrice\":" + itemTotalPrice +
                "},\"newTotal\":" + cartTotal + "}");
    }

    private void removeCartItemFromCookies(int id, HttpServletRequest req, HttpServletResponse resp) {
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("cartItem_" + id, null);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
