package com.spring.boot.journal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Contact;
import com.spring.boot.journal.entities.MotDePasseOublie;
import com.spring.boot.journal.views.Views;

@Controller
public class EntryController{
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_ACCUEIL.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/acceuil", method = RequestMethod.GET)
	public ModelAndView acceuil(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_ACCUEIL.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView inscription(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_LOGIN.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public ModelAndView contact(ModelAndView modelAndView){
		modelAndView.addObject("contact",new Contact());
		modelAndView.setViewName(Views.VIEW_CONTACT.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/contactSuccess", method = RequestMethod.GET)
	public ModelAndView contactSuccess(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_CONTACT_SUCCESS.getPage());
		return modelAndView;
	}
	
	@GetMapping(value="/motDePasseOublie")
	public ModelAndView motDePasseOublie(ModelAndView modelAndView){
		modelAndView.addObject("mdpOublie", new MotDePasseOublie());
		modelAndView.setViewName(Views.VIEW_MOT_DE_PASSE_OUBLIE.getPage());
		return modelAndView;
	}
	
}
