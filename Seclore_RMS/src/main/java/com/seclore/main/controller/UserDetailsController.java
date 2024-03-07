package com.seclore.main.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.UserDetailsServiceInterface;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserDetailsController {
	@Autowired
	private UserDetailsServiceInterface userDetailsServiceInterface;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage() {
		UserDetails userDetails = new UserDetails();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "userlogin", method = RequestMethod.POST)
	public ModelAndView Login(@ModelAttribute UserDetails user, Model model, HttpServletRequest request) {
		String message = "";
		ModelAndView modalAndView = new ModelAndView();
		UserDetails outUser = userDetailsServiceInterface.userLogin(user.getUserId(), user.getPassword());
		if (outUser == null) {
			message = " User doesnot exist or is blocked ";
			modalAndView.setViewName("login");
		} else {
			HttpSession session = request.getSession();
			user.setPassword(null);
			session.setAttribute("getroomrequirements", user);
			message = " User Loggedin successfully ";

			if (user.getPosition() == "admin")
				modalAndView.setViewName("admindashboard");
			else
				modalAndView.setViewName("userdashboard");

		}
		model.addAttribute("message", message);
		return modalAndView;
	}

	@RequestMapping(value = "/updatepassword", method = RequestMethod.GET)
	public String showUpdatepassword() {
		return "updatepassword";
	}

	@RequestMapping(value = "updatepass", method = RequestMethod.POST)
	public ModelAndView updatePassword(HttpServletRequest request, Model model) {
		String message = "";
		HttpSession session = request.getSession();
		UserDetails user = (UserDetails) session.getAttribute("loggedInUser");
		int uid = user.getUserId();
		ModelAndView modalAndView = new ModelAndView();
		modalAndView.setViewName("dashboard");
		if (request.getParameter("newpass").equals(request.getParameter("renewpass"))) {
			message = "Passwords do not match";
		} else if (userDetailsServiceInterface.updatePassword(uid, request.getParameter("oldpass"),
				request.getParameter("newpass"))) {
			message = "success fully updated password";
		} else {
			message = "failed to update password";
		}
		model.addAttribute("message", message);
		return modalAndView;
	}

	@RequestMapping(value = "/updateprofile", method = RequestMethod.GET)
	public ModelAndView showUpdateProfile(HttpSession ses) {
		UserDetails userDetails = (UserDetails) ses.getAttribute("loggedInUser");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("updateprofile");
		return modelAndView;
	}

	@RequestMapping(value = "updateprofile", method = RequestMethod.POST)
	public ModelAndView updateUserProfile(@ModelAttribute UserDetails user, HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		userDetailsServiceInterface.updateUserDetails(user);
		HttpSession session = request.getSession();
		session.setAttribute("loggedInUser", user);
		modelAndView.setViewName("userdashboard");
		return modelAndView;
	}
	
	

	@RequestMapping(value = "getallusers", method = RequestMethod.GET)
	public ModelAndView getAllUsers(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userlist");
		List<UserDetails> usersList = userDetailsServiceInterface.getAllUsers();
		modelAndView.addObject("userlist", usersList);
		return modelAndView;
	}

	@RequestMapping(value = "logout")
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "adduser", method = RequestMethod.GET)
	public ModelAndView showAddUser() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adduser");

		UserDetails userDetails = new UserDetails();
		modelAndView.addObject("userDetails", userDetails);
			
		return modelAndView;
	}

	@RequestMapping(value = "addnewuser", method = RequestMethod.POST)
	public void addNewUser(@ModelAttribute UserDetails user, HttpServletRequest request, HttpServletResponse response) {
		try {
			userDetailsServiceInterface.addNewUser(user);
			HttpSession session = request.getSession();
			session.setAttribute("message", "successfully updated ");
			response.sendRedirect("admindashboard");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "updateuserstatus", method = RequestMethod.POST)
	public void updateUserStatus(HttpServletRequest request, HttpServletResponse response) {
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			boolean isActive = request.getParameter("isactive").equals("activate");
			userDetailsServiceInterface.updateActive(userId, isActive);
			response.sendRedirect("getallusers");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@RequestMapping(value = "adduser", method = RequestMethod.GET)
	public String showDashBoard(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDetails user = (UserDetails)session.getAttribute("loggedInUser");
		if(user.getPosition()=="admin")
			return "admindashboard";
		return "userdashboard";
	}
	
	
}
