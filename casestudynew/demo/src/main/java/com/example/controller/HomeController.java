package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeController", urlPatterns = {"/"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // Thiết lập mã hóa cho yêu cầu
        response.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa cho phản hồi
        response.setContentType("text/html;charset=UTF-8"); // Đảm bảo trang web sử dụng UTF-8
        request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // Thiết lập mã hóa cho yêu cầu
        response.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa cho phản hồi
        response.setContentType("text/html;charset=UTF-8"); // Đảm bảo trang web sử dụng UTF-8
        doGet(request, response);
    }
}
