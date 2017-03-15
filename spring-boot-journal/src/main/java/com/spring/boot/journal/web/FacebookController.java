package com.spring.boot.journal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacebookController {

	@Autowired
	private Facebook facebook;
    
	@Autowired
	private ConnectionRepository connectionRepository;
    
	@GetMapping
    public String helloFacebook(Model model) {
        
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
        
		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        return "/acceuil";
    }
}
