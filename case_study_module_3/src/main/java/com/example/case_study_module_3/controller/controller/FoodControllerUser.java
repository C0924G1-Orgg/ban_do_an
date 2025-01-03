package com.example.case_study_module_3.controller.controller;

import com.example.case_study_module_3.controller.model.Food;
import com.example.case_study_module_3.controller.service.IFoodService;
import com.example.case_study_module_3.controller.service.impl.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "foodControllerUser", urlPatterns = "/thuc-don")
public class FoodControllerUser extends HttpServlet {
    private final IFoodService foodService = new FoodService();

    public FoodControllerUser() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> foods = foodService.getAll();
        request.setAttribute("foods", foods);
        request.getRequestDispatcher("/user/food_list.jsp").forward(request, response);
        System.out.println("Danh sách thực đơn:");
        foods.forEach(System.out::println);
    }
}
