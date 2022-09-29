package com.hillel.fedorenko.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/FindWord")
public class FindWordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("findword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String input = req.getParameter("input");
        InputStream inputStream = getClass().getResourceAsStream("/text.txt");
        if (inputStream != null) {
            int i;
            List<Character> list = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            while ((i = inputStream.read()) != -1) {
                list.add((char) i);
            }
            list.forEach(stringBuilder::append);
            Pattern pattern = Pattern.compile("\\b" + input + "\\b");
            Matcher matcher = pattern.matcher(stringBuilder);
            resp.getWriter().println("Find in file: " + matcher.results().count());
        } else {
            resp.getWriter().print("Sorry, cannot find file on server");
        }
    }
}
