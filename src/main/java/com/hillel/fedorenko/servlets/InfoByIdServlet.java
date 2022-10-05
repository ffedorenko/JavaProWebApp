package com.hillel.fedorenko.servlets;

import com.hillel.fedorenko.jdbc.entity.Order;
import com.hillel.fedorenko.jdbc.utils.StoreService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Task 1", value = "/Task1")
public class InfoByIdServlet extends HttpServlet {
    StoreService storeService = new StoreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/task1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String input = request.getParameter("input");
        int inputNumber = Integer.parseInt(input);
        Order order = storeService.getOrderById(inputNumber);
        if (order == null) {
            request.setAttribute("check", 0);
        }
        request.setAttribute("output", order);
        request.getRequestDispatcher("/JSP/task1.jsp").forward(request, response);
    }
}
