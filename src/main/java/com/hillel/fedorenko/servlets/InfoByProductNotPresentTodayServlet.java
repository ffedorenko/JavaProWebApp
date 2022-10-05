package com.hillel.fedorenko.servlets;

import com.hillel.fedorenko.jdbc.entity.Order;
import com.hillel.fedorenko.jdbc.utils.StoreService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Task 4", value = "/Task4")
public class InfoByProductNotPresentTodayServlet extends HttpServlet {
    StoreService storeService = new StoreService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/task4.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String input = request.getParameter("input");
        List<Order> orderList = storeService.getOrdersByProductNotPresentToday(input);
        if (orderList.isEmpty()) {
            request.setAttribute("check", 0);
        }
        request.setAttribute("output", orderList);
        request.getRequestDispatcher("/JSP/task4.jsp").forward(request, response);
    }
}
