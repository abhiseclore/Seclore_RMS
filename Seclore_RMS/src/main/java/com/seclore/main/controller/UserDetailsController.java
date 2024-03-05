package com.seclore.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.UserDetailsServiceInterface;

@Controller
public class UserDetailsController {
	@Autowired
	private UserDetailsServiceInterface userDetailsServiceInterface;
	@RequestMapping("login")
	public ModelAndView Login() {
		UserDetails userDetails = new UserDetails();
		
	}
}
