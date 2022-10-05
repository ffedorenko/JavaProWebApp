package com.hillel.fedorenko.servlets;

import com.hillel.fedorenko.jdbc.entity.Order;
import com.hillel.fedorenko.jdbc.utils.StoreService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Task 2", value = "/Task2")
public class InfoByMaxPriceAndDistinctProductsServlet extends HttpServlet {
    StoreService storeService = new StoreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/task2.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputSum = request.getParameter("sum");
        String inputCountOfDistinct = request.getParameter("distinct");
        double sum = Double.parseDouble(inputSum);
        int distinct = Integer.parseInt(inputCountOfDistinct);
        List<Order> orderList = storeService.infoByMaxSumAndCountOfDistinct(sum, distinct);
        if (orderList.isEmpty()) {
            request.setAttribute("check", 0);
        }
        request.setAttribute("output", orderList);
        request.getRequestDispatcher("/JSP/task2.jsp").forward(request, response);
    }
}
