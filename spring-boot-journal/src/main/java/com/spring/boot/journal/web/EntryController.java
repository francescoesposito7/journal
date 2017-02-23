package com.spring.boot.journal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.services.NewsRestService;
import com.spring.boot.journal.views.Views;


@Controller
public class EntryController{
	
	@Autowired
	NewsRestService serviceNews;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_INDEX.getPage());
		//modelAndView.addObject("journal", repo.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value="/acceuil", method = RequestMethod.GET)
	public ModelAndView acceuil(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_ACCUEIL.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/inscription", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_INSCRIPTION.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/news", method = RequestMethod.GET)
	public ModelAndView news(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_NEWS.getPage());
		modelAndView.addObject("listNews", serviceNews.listNews());
		return modelAndView;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView inscription(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_LOGIN.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public ModelAndView contact(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_CONTACT.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/inscform", method = RequestMethod.GET)
	public ModelAndView inscFormulaire(ModelAndView modelAndView){
		modelAndView.addObject("utilisateur", new Utilisateur());
		modelAndView.setViewName(Views.VIEW_INSCFORM.getPage());
		return modelAndView;
	}
	
}
