package com.hillel.fedorenko.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sort.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nonSorted = req.getParameter("input");
        String[] numbersIn = nonSorted.split(",");
        List<Integer> sorted = Arrays.stream(numbersIn).map(Integer::parseInt).sorted().toList();
        resp.getWriter().println(sorted);
    }
}
