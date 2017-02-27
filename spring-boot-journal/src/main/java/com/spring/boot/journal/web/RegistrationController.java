package com.spring.boot.journal.web;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
    private MessageSource messages;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UtilisateurRepository userRepository;

	
	 @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	    public String confirmRegistration(final Locale locale, 
	    								  final Model model,
	    								  @RequestParam("token") final String token) throws UnsupportedEncodingException {
	        
	    	final String result = userService.validateVerificationToken(token);
	    	final Utilisateur user = userService.getUser(token);
	    	
	    	if (result.equals("valid")) {

	            //model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
	            return "redirect:/login";
	        }else {
	        	userRepository.delete(user);
	        }

	    	
	    	
	       //model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
	        //model.addAttribute("expired", "expired".equals(result));
	        //model.addAttribute("token", token);
	        
	        return "redirect:/badUser";
	    }
	 
	 
	 @RequestMapping(value = "/resendPassword", method = RequestMethod.GET)
	    public String resendPassword(final Locale locale, 
	    							 final Model model,
	    							 @RequestParam("token") final String token) throws UnsupportedEncodingException {
	        
	    	final String result = userService.validateVerificationToken(token);
	    	final Utilisateur user = userService.getUser(token);
	    	
	    	if (result.equals("valid")) {
	    		userRepository.save(user);
	            return "redirect:/login";
	        }

	    	
	    	
	       /* model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
	        model.addAttribute("expired", "expired".equals(result));
	        model.addAttribute("token", token);
	        */
	        return "redirect:/badUser";
	    }
}
