package com.seclore.main.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.servlet.HandlerInterceptor;

import com.seclore.main.domain.UserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ValidationInterceptor implements HandlerInterceptor {
	Set<String> adminUri = new HashSet<String>();
	Set<String> userUri = new HashSet<String>();

	public ValidationInterceptor() {
		adminUri.add("adduser");
		adminUri.add("addroom");
		adminUri.add("admindashboard");
		adminUri.add("login");
		adminUri.add("userlist");
		adminUri.add("updatepassword");
		adminUri.add("allrooms");
		adminUri.add("getbookingrequirements");
		adminUri.add("updateprofile");
		adminUri.add("updateroom");
		adminUri.add("add");

		userUri.add("userdashboard");
		userUri.add("login");
		userUri.add("updatepassword");
		userUri.add("allrooms");
		userUri.add("getbookingrequirements");
		userUri.add("updateprofile");
		userUri.add("add");

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("loggedInUser") == null) {
			response.sendRedirect("login");
			return false;
		}
		UserDetails user = (UserDetails) httpSession.getAttribute("loggedInUser");
		String uri = request.getRequestURI();

		if (user.getPosition().equals("admin")) {
			if (adminUri.contains(uri)) {
				return true;
			}
			response.sendRedirect("admindashboard");
			return false;
		} else {
			if (userUri.contains(uri)) {
				return true;
			}
			response.sendRedirect("userdashboard");
			return false;
		}

	}
}
