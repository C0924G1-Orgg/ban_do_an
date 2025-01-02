package com.example.case_study_module_3.controller.controller;

import com.example.case_study_module_3.controller.dto.FoodDTO;
import com.example.case_study_module_3.controller.model.Food;
import com.example.case_study_module_3.controller.service.IFoodService;
import com.example.case_study_module_3.controller.service.IRestaurantService;
import com.example.case_study_module_3.controller.service.impl.FoodService;
import com.example.case_study_module_3.controller.service.impl.RestaurantService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "foodController", urlPatterns = "/food")
public class FoodController extends HttpServlet {
    private static final String ACTION = "action";
    private static final String MESSAGE = "message";

    private static final String CREATE_SUCCESS = "New food item added successfully";
    private static final String DELETE_SUCCESS = "Food item deleted successfully";
    private static final String NOT_FOUND = "Food item not found.";

    private final IFoodService foodService = new FoodService();
    private final IRestaurantService restaurantService = new RestaurantService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");  // Thiết lập mã hóa cho yêu cầu
        resp.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa cho phản hồi
        resp.setContentType("text/html;charset=UTF-8"); // Đảm bảo trang web sử dụng UTF-8
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
        req.setCharacterEncoding("UTF-8");  // Thiết lập mã hóa cho yêu cầu
        resp.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa cho phản hồi
        resp.setContentType("text/html;charset=UTF-8"); // Đảm bảo trang web sử dụng UTF-8
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
        req.setAttribute("restaurants", restaurantService.getAll());
        req.getRequestDispatcher("/admin/create.jsp").forward(req, resp);
    }

    private void handleUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Food food = foodService.findByID(id);
            if (food != null) {
                req.setAttribute("food", food);
                req.setAttribute("restaurants", restaurantService.getAll());
                req.getRequestDispatcher("/admin/update.jsp").forward(req, resp);
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
            if (foodService.deleteById(id)) {
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
        List<FoodDTO> foods = foodService.getAllDTO();
        req.setAttribute("foods", foods);
        req.getRequestDispatcher("/admin/list.jsp").forward(req, resp);
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String priceStr = req.getParameter("price");
        String restaurantIdStr = req.getParameter("restaurantId");

        try {
            double price = Double.parseDouble(priceStr);
            int restaurantId = Integer.parseInt(restaurantIdStr);

            if (price <= 0) {
                req.setAttribute("error", "Price must be a positive number. Please enter a valid price.");
                req.setAttribute("name", name);
                req.setAttribute("description", description);
                req.setAttribute("price", priceStr);
                req.setAttribute("restaurants", restaurantService.getAll());
                req.getRequestDispatcher("/admin/create.jsp").forward(req, resp);
                return;
            }

            Food newFood = new Food(0, restaurantId, name, description, price);
            foodService.save(newFood);
            redirectWithMessage(resp, CREATE_SUCCESS);

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid format for price. Please enter a valid number (e.g., 12.34).");
            req.setAttribute("name", name);
            req.setAttribute("description", description);
            req.setAttribute("price", priceStr);
            req.setAttribute("restaurants", restaurantService.getAll());
            req.getRequestDispatcher("/admin/create.jsp").forward(req, resp);
        }
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String priceStr = req.getParameter("price");
        String restaurantIdStr = req.getParameter("restaurantId");

        try {
            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);
            int restaurantId = Integer.parseInt(restaurantIdStr);

            if (price <= 0) {
                req.setAttribute("error", "Price must be a positive number. Please enter a valid price.");
                req.setAttribute("food", new Food(id, restaurantId, name, description, price));
                req.setAttribute("restaurants", restaurantService.getAll());
                req.getRequestDispatcher("/admin/update.jsp").forward(req, resp);
                return;
            }

            Food updatedFood = new Food(id, restaurantId, name, description, price);
            if (foodService.update(updatedFood)) {
                redirectWithMessage(resp, "Food updated successfully.");
            } else {
                redirectWithMessage(resp, NOT_FOUND);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid format for price. Please enter a valid number (e.g., 12.34).");
            req.setAttribute("food", new Food(0, 0, name, description, 0.0)); // Dummy food for retry
            req.setAttribute("restaurants", restaurantService.getAll());
            req.getRequestDispatcher("/admin/update.jsp").forward(req, resp);
        }
    }


    private void redirectWithMessage(HttpServletResponse resp, String message) throws IOException {
        resp.sendRedirect("/food?" + MESSAGE + "=" + message);
    }

}
