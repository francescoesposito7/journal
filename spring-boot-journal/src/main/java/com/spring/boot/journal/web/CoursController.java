package com.spring.boot.journal.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.repository.CoursRepository;
import com.spring.boot.journal.views.Views;

@Controller
public class CoursController {

	@Autowired
	private CoursRepository coursRepository;
	
	@GetMapping("/cours")
	public ModelAndView listNews(ModelAndView modelAndView){
		
		List<Cours> cours = new ArrayList<Cours>();		
		cours = coursRepository.findAll();
		
		modelAndView.addObject("listCours", cours);
		modelAndView.setViewName(Views.VIEW_COURS.getPage());
		
		return modelAndView;
	}
}
