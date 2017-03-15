package com.spring.boot.journal.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.views.Views;

@Controller
@RequestMapping("/fb")
public class FacebookController {

	@Autowired
	private Facebook facebook;
    
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired
    private UtilisateurRepository rep;
	
	@GetMapping
    public String helloFacebook(ModelAndView model) {
        
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			
			return "/login";
        }
		
		model.addObject("facebookProfile", facebook.userOperations().getUserProfile());
		
		Utilisateur user = new Utilisateur();
		Connection<?> connection = connectionRepository.getPrimaryConnection(Facebook.class);
		
		user.setUsername(connection.getDisplayName());
		user.setNom(facebook.userOperations().getUserProfile().getName());
        user.setPrenom(facebook.userOperations().getUserProfile().getLastName());
        
        model.addObject("user", user);        
        
		model.addObject("facebookProfile", facebook.userOperations().getUserProfile());
        
		
		return "/completeInscription";
    }
	
	
	@PostMapping("/saveUserFb")
	public String saveUserFb(Utilisateur user){
		
		rep.save(user);
		
		return "/acceuil";
	}
	
	
}
