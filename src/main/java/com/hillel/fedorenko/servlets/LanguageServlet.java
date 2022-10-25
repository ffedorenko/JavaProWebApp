package com.hillel.fedorenko.servlets;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.jsp.jstl.core.Config;

import java.io.IOException;
import java.io.Serial;

@WebServlet(name = "LanguageServlet", value = "/language")
public class LanguageServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String localeToSet = request.getParameter("locale");

        HttpSession session = request.getSession();
        Config.set(session, "jakarta.servlet.jsp.jstl.fmt.locale", localeToSet);

        response.sendRedirect("/JavaProWebApp");

    }
}
