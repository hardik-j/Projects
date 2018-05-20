package com.neu.hardik.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neu.hardik.pojo.User;
import com.neu.hardik.utility.Constants.Role;

public class AuthorizationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String url = request.getRequestURI();
		if (user == null)
			response.sendRedirect(request.getContextPath() + "/login");
		if (url.contains("meeting") || url.contains("register")) {
			if (!user.getRoles().contains(Role.ADMIN))
				response.sendRedirect(request.getContextPath() + "/task/mytask");
		} else if (url.contains("search")) {
			if (!user.getRoles().contains(Role.MANAGER))
				response.sendRedirect(request.getContextPath() + "/task/mytask");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
