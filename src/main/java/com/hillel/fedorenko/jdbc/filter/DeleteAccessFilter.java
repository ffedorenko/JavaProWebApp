package com.hillel.fedorenko.jdbc.filter;

import com.hillel.fedorenko.jdbc.entity.User;
import com.hillel.fedorenko.jdbc.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/Task6")
public class DeleteAccessFilter extends HttpFilter implements Filter {

	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (METHOD_GET.equals(httpRequest.getMethod())) {
			chain.doFilter(request, response);
		} else if (METHOD_POST.equals(httpRequest.getMethod())) {
			User user = (User) httpRequest.getSession().getAttribute("user");
			if (nonNull(user) && UserRole.ADMIN == user.getUserRole()) {
				chain.doFilter(request, response);
			} else {
				httpRequest.setAttribute("errorMessage",
						"Non-authorized user with non-admin role not allowed to access delete");
				httpRequest.getRequestDispatcher("/JSP/task6.jsp").forward(httpRequest, response);
			}
		}

	}

}
