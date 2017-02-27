package com.spring.boot.journal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.views.Views;

@Controller
public class InscriptionController {
	
	@RequestMapping(value="/inscription", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_INSCRIPTION.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/inscform", method = RequestMethod.GET)
	public ModelAndView inscFormulaire(ModelAndView modelAndView,
			@RequestParam (name="type", defaultValue="0") String param){
		Utilisateur user = new Utilisateur();
		user.setTypeCompte(Integer.valueOf(param));
		modelAndView.addObject("utilisateur", user);
		modelAndView.setViewName(Views.VIEW_INSCFORM.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/iscriptionSuccess", method = RequestMethod.GET)
	public ModelAndView contactSuccess(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_INSCRIPTION_SUCCESS.getPage());
		return modelAndView;
	}
}
