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

	
	@RequestMapping("/")
	public String showDefaultPage() {
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage() {
		UserDetails userDetails = new UserDetails();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping("userlogin")
	public String Login(@ModelAttribute UserDetails user, HttpSession session) {
		String message = "";
		String nextPage = "";
		UserDetails outUser = userDetailsServiceInterface.userLogin(user.getUserId(), user.getPassword());
		if (outUser == null) {
			message = " INVALID USER_ID OR PASSWORD ";
			nextPage = "login";
		} else {
			user.setPassword(null);
			session.setAttribute("loggedInUser", outUser);
			nextPage = "redirect:/dashboard";

		}
		session.setAttribute("message", message);
		return nextPage;
	}

	@RequestMapping(value = "updatepassword", method = RequestMethod.GET)
	public String showUpdatepassword() {
		return "updatepassword";
	}

	@RequestMapping(value = "updatepassword", method = RequestMethod.POST)
	public String updatePassword(HttpServletRequest request) {
		String message = "";
		HttpSession session = request.getSession();
		UserDetails user = (UserDetails) session.getAttribute("loggedInUser");
		System.out.println("Session : ");
		System.out.println(user);
		int uid = user.getUserId();
		if (!request.getParameter("newpass").equals(request.getParameter("renewpass"))) {
			message = "Passwords do not match";
		} else if (userDetailsServiceInterface.updatePassword(uid, request.getParameter("oldpass"),
				request.getParameter("newpass"))) {
			message = "success fully updated password";
		} else {
			message = "failed to update password";
		}
		session.setAttribute("message", message);
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "updateprofile", method = RequestMethod.GET)
	public ModelAndView showUpdateProfile(HttpSession ses) {
		UserDetails userDetails = (UserDetails) ses.getAttribute("loggedInUser");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("updateprofile");
		return modelAndView;
	}

	@RequestMapping(value = "updateprofile", method = RequestMethod.POST)
	public String updateUserProfile(@ModelAttribute UserDetails user, HttpSession session) {
		userDetailsServiceInterface.updateUserDetails(user);
		session.setAttribute("loggedInUser", user);
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "getallusers", method = RequestMethod.GET)
	public ModelAndView getAllUsers(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userlist");
		List<UserDetails> usersList = userDetailsServiceInterface.getAllUsers();
		model.addAttribute("userlist", usersList);
		return modelAndView;
	}

	@RequestMapping(value = "logout")
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	public String updateUserStatus(HttpServletRequest request) {
		
			int userId = Integer.parseInt(request.getParameter("userId"));
			System.out.println(userId);
			boolean isActive = request.getParameter("isactive").equals("activate");
			userDetailsServiceInterface.updateActive(userId, isActive);
			return "redirect:/getallusers";
		
	}

	@RequestMapping("userdashboard")
	public String showUserDashboard() {
		return "userdashboard";
	}

	@RequestMapping("admindashboard")
	public String showAdminDashboard() {
		return "admindashboard";
	}

	@RequestMapping("dashboard")
	public String showDashBoard(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		UserDetails user = (UserDetails) session.getAttribute("loggedInUser");
		System.out.println(user);
		if (user.getPosition().equals("admin")||user.getPosition().equals("Admin")||user.getPosition().equals("Administrator"))
			return "redirect:/admindashboard";
		
		return "redirect:/userdashboard";
	}

	@RequestMapping(value = "getbookingrequirements",method = RequestMethod.GET)
	public String getbookingrequirements() {

		System.out.println("Response commited");
		return "getbookingrequirements";
	}

}
