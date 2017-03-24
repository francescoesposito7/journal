package com.spring.boot.journal.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.views.Views;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@RequestMapping(value="/home")
	public ModelAndView home(ModelAndView model){
		model.setViewName(Views.VIEW_ADMIN_HOME.getPage());
		return model;
	}

}
