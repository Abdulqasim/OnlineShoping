package com.pranay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pranay.model.User;
import com.pranay.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;

	// REGISTER
	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		this.us.register(user);
		return "redirect:/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "registration-form";
	}

	@GetMapping("/login")
	public String loginForm() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/mobiles";
	}

}
