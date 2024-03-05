package com.seclore.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.seclore.main.service.UserDetailsServiceInterface;

@Controller
public class UserDetailsController {
	@Autowired
	private UserDetailsServiceInterface userDetailsServiceInterface;

}
