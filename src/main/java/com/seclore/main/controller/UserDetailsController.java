package com.seclore.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.login.main.domain.User;
import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.UserDetailsServiceInterface;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;
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
			session.setAttribute("loggedInUser", user);
			message = " User Loggedin successfully ";

			modalAndView.setViewName("dashboard");
		}
		model.addAttribute("message", message);
		return modalAndView;
	}
}
