package com.hillel.fedorenko.servlets;

import com.hillel.fedorenko.jdbc.entity.Order;
import com.hillel.fedorenko.jdbc.utils.StoreService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Task 5", value = "/Task5")
public class NewOrderFromProductsOrderedTodayServlet extends HttpServlet {
    StoreService storeService = new StoreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/task5.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int result = storeService.createOrderFromProductsOrderedToday();
        if (result > 0) {
            Order order = storeService.getOrderByMaxId();
            request.setAttribute("output", result);
            request.setAttribute("order", order);
        } else {
            request.setAttribute("output", result);
        }
        request.getRequestDispatcher("/JSP/task5.jsp").forward(request, response);
    }
}
