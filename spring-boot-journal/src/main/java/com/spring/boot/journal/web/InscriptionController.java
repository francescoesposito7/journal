package com.spring.boot.journal.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.service.impl.UserServiceImpl;
import com.spring.boot.journal.views.Views;

@Controller
public class InscriptionController {
	
	@Autowired
	private UserServiceImpl service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
	
	@RequestMapping(value="/tempoFacebook", method = RequestMethod.GET)
	public ModelAndView tempoFacebook(HttpServletRequest request,ModelAndView modelAndView,
			@RequestParam(name="id") String pId,
			@RequestParam(name="email") String pEmail){
			
		if( service.findUserbyFacebookId( Long.valueOf(pId) ) == null ){
			
			Utilisateur user = new Utilisateur();
			
			user.setFacebookId(Long.valueOf(pId));
			user.setUsername(request.getRemoteUser());
			
			if(pEmail.equals("null")){
	        	String p = "";
	        	user.setEmail(p);
			}else{
				user.setEmail(pEmail);
			}
			
			String p=RandomStringUtils.randomAlphanumeric(10);
			String pw=passwordEncoder.encode(p);
			user.setPassword(pw);
			user.setPasswordConfirmation(user.getPassword());
			
			modelAndView.addObject("utilisateur", user);
			modelAndView.setViewName(Views.VIEW_INSCFACEBOOK.getPage());
			return modelAndView;
		}
		modelAndView.setViewName(Views.VIEW_ACCUEIL.getPage());
		return modelAndView;
	}
}
