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
import org.springframework.web.servlet.ModelAndView;

import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.UserDetailsServiceInterface;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserDetailsController {
	@Autowired
	private UserDetailsServiceInterface userDetailsServiceInterface;

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

			if(user.getPosition() == "admin")
			modalAndView.setViewName("admindashboard");
			else
				modalAndView.setViewName("userdashboard");

		}
		model.addAttribute("message", message);
		return modalAndView;
	}
	
	
	@RequestMapping(value = "updatepass", method = RequestMethod.POST)
	public ModelAndView updatePassword(HttpServletRequest request, Model model) {
		String message = "";
		HttpSession session = request.getSession();
		UserDetails user = (UserDetails) session.getAttribute("loggedInUser");
		int uid = user.getUserId();
		ModelAndView modalAndView = new ModelAndView();
		modalAndView.setViewName("dashboard");
		if(request.getParameter("newpass").equals(request.getParameter("renewpass"))) {
			message = "Passwords do not match";	
		}
		else if(userDetailsServiceInterface.updatePassword(uid,request.getParameter("oldpass") , request.getParameter("newpass"))) {
			message="success fully updated password";
		}
		else {
			message = "failed to update password";
		}
		model.addAttribute("message", message);
		return modalAndView;
	}
	
	@RequestMapping(value = "updateprofile", method= RequestMethod.POST)
	public ModelAndView updateUserProfile(@ModelAttribute UserDetails user,HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		userDetailsServiceInterface.updateUserDetails(user);
		HttpSession session = request.getSession();
		session.setAttribute("loggedInUser", user);
		modelAndView.setViewName("userdashboard");
		return modelAndView; 
	}
	
	@RequestMapping(value = "getallusers", method = RequestMethod.POST)
	public ModelAndView getAllUsers(HttpServletRequest request,Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewusers");
		List<UserDetails> usersList = userDetailsServiceInterface.getAllUsers();
		modelAndView.addObject("userlist", usersList);
		return modelAndView;
	}
	
	@RequestMapping(value = "logout")
	public void getAllUsers(HttpServletRequest request, HttpServletResponse response ) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="addnewuser" , method = RequestMethod.POST)
	public void addNewUser(@ModelAttribute UserDetails user, HttpServletRequest request, HttpServletResponse response)
	{
		try {
		userDetailsServiceInterface.addNewUser(user);
		HttpSession session = request.getSession();
		session.setAttribute("message", "successfully updated ");
		response.sendRedirect("admindashboard");
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
