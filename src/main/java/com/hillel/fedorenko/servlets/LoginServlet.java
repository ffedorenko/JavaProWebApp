package com.hillel.fedorenko.servlets;

import com.hillel.fedorenko.jdbc.entity.User;
import com.hillel.fedorenko.jdbc.utils.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;

	private final UserService userService;

	public LoginServlet() {
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		Optional<User> found = userService.findByNameAndPassword(name, password);

		if (found.isEmpty()) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("user", found.get());
			response.sendRedirect("/JavaProWebApp");
		}

	}

}
