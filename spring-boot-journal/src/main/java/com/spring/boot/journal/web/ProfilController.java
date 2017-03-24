package com.spring.boot.journal.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.ChangedPassword;
import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.service.UserService;
import com.spring.boot.journal.views.Views;

@Controller
/*@RequestMapping(value="/profil")
*/public class ProfilController {
	
	@Autowired
	UserService service;
	
	@GetMapping(value="/profil")
	public ModelAndView profil(ModelAndView modelAndView, HttpServletRequest req){
		Utilisateur user = service.findUserbyUsername(req.getRemoteUser());
		modelAndView.addObject("user", user);
		modelAndView.setViewName(Views.VIEW_PROFIL.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/parametres", method=RequestMethod.GET)
	public ModelAndView param(HttpServletRequest req, ModelAndView modelAndView){
		String remote = req.getRemoteUser();
		Utilisateur user = service.findUserbyUsername(remote);
		modelAndView.addObject("user", user);
		modelAndView.addObject("changedPassword", new ChangedPassword());
		modelAndView.setViewName(Views.VIEW_PROFIL_PARAM.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/courses", method=RequestMethod.GET)
	public ModelAndView myCourses(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_PROFIL_COURS.getPage());
		return modelAndView;
	}
	
	@RequestMapping(value="/messages", method=RequestMethod.GET)
	public ModelAndView myMess(ModelAndView modelAndView){
		modelAndView.setViewName(Views.VIEW_PROFIL_MESS.getPage());
		return modelAndView;
	}
}
