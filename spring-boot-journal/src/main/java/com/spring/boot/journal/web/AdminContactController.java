package com.spring.boot.journal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Contact;
import com.spring.boot.journal.repository.ContactRepository;
import com.spring.boot.journal.views.Views;

@Controller
@RequestMapping(value="/admin")
public class AdminContactController {

	@Autowired
	private ContactRepository contactRepository;
	
	@RequestMapping(value="/home")
	public ModelAndView home(ModelAndView model,
						@RequestParam(name="page",defaultValue="0")int p){
		Page<Contact> contacts=contactRepository.findAll(new PageRequest(p, 10));

		int pageCount=contacts.getTotalPages();
		
		int pages[]=new int[pageCount];
		
		for(int i=0;i<pageCount;i++){pages[i]=i;}
		
		model.addObject("pages", pages);
		model.addObject("pageMail",contacts);
		model.addObject("pageCourante", p);
		model.setViewName(Views.VIEW_ADMIN_HOME.getPage());
		return model;
	}
	
	@RequestMapping(value="/edit")
	public String editContenu(@ModelAttribute("source") String source, ModelAndView model){
		model.addObject("source", source);
		return "admin/creationContenu";
	}
	
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id,ModelAndView model){
		
		contactRepository.delete(id);
		
		return "redirect:home";
	}
	
	@RequestMapping(value="/answer")
	public String edit(Long id){
		
		return "adminConsole";
	}
	
	@PostMapping
	public String saveSource(@ModelAttribute("source") String source){
		System.out.println(source);
		return "admin/creationContenu";
	}
}
