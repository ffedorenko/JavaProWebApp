package com.hillel.fedorenko.servlets;

import com.hillel.fedorenko.jdbc.utils.StoreService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Task 6", value = "/Task6")
public class DeleteServlet extends HttpServlet {
    StoreService storeService = new StoreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/task6.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputName = request.getParameter("product");
        String inputCount = request.getParameter("count");
        int result = storeService.deleteOrderByAmountOfProductName(inputName, Integer.parseInt(inputCount));
        request.setAttribute("output", result);
        request.getRequestDispatcher("/JSP/task6.jsp").forward(request, response);
    }
}
