package com.spring.boot.journal.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping("/403")
	private String accessDenied(){
		return "403";
	}
	
	@RequestMapping("/logout")
	private String logout(HttpServletRequest request){
		try {
			request.logout();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/login";

	}
	
	@RequestMapping("/")
	private String home(){
		
		return "redirect:/acceuil";
	}

}