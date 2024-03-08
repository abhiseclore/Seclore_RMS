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
		adminUri.add("/adduser");
		adminUri.add("/admindashboard");
		adminUri.add("/login");
		adminUri.add("/userlist");
		adminUri.add("/updatepassword");
		adminUri.add("/roomdetails/allrooms");
		adminUri.add("/roomdetails/addroom");
		adminUri.add("/roomdetails/updateroom");
		adminUri.add("/getbookingrequirements");
		adminUri.add("/updateprofile");
		adminUri.add("/updateroom");
		adminUri.add("/getallusers");
		adminUri.add("/bookingdetails/showallbookingsbyadmin");
		adminUri.add("/logout");
		adminUri.add("/bookingdetails/add");

		adminUri.add("/bookingdetails/delete");
		adminUri.add("/bookingdetails/update");
		adminUri.add("/bookingdetails/get");
		adminUri.add("/bookingdetails/updateslot");
		
		userUri.add("/userdashboard");
		userUri.add("/login");
		userUri.add("/updatepassword");
		userUri.add("/getbookingrequirements");
		userUri.add("/bookingdetails/showallbookingsbyadmin");
		userUri.add("/updateprofile");
		userUri.add("/add");
		userUri.add("/logout");
		userUri.add("/bookingdetails/showallbookings");
		userUri.add("/bookingdetails/add");
		userUri.add("/bookingdetails/delete");
		userUri.add("/bookingdetails/update");
		userUri.add("/bookingdetails/get");
		userUri.add("/bookingdetails/updateslot");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession();

		UserDetails user = (UserDetails) httpSession.getAttribute("loggedInUser");
		String uri = request.getRequestURI();
		
		if(uri.equals("/") ||  uri.equals("/login") || uri.equals("/userlogin"))
		{
			return true;
		}
		
		if(httpSession.getAttribute("loggedInUser") == null) {
			response.sendRedirect("/login");
			return false;
		}
		
		if (user.getPosition().equals("admin")) {
			if (adminUri.contains(uri)) {
				return true;
			}
			response.sendRedirect("/admindashboard");
			return false;
		} else {
			if (userUri.contains(uri)) {
				return true;
			}
			response.sendRedirect("/userdashboard");
			return false;
		}

	}
}
