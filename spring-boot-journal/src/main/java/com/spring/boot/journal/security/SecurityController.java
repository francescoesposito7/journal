package com.spring.boot.journal.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping("/403")
	private String accessDenied(){
		return "403";
	}
	
	@RequestMapping("/login")
	private String login(){
		return "login";
	}
	
	@RequestMapping("/")
	private String home(){
		return "redirect:/acceuil";
	}
	

}