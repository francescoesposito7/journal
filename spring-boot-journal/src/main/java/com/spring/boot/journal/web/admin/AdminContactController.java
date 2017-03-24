package com.spring.boot.journal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Contact;
import com.spring.boot.journal.repository.ContactRepository;
import com.spring.boot.journal.repository.NewsFeedRepository;
import com.spring.boot.journal.views.Views;

@Controller
@RequestMapping(value="/admin")
public class AdminContactController {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private NewsFeedRepository feedRepository;
	
	@RequestMapping(value="/contact")
	public ModelAndView contacts(ModelAndView model,
						@RequestParam(name="page",defaultValue="0")int p){
		Page<Contact> contacts=contactRepository.findAll(new PageRequest(p, 10));

		int pageCount=contacts.getTotalPages();
		
		int pages[]=new int[pageCount];
		
		for(int i=0;i<pageCount;i++){pages[i]=i;}
		
		model.addObject("pages", pages);
		model.addObject("pageMail",contacts);
		model.addObject("pageCourante", p);
		model.setViewName(Views.VIEW_ADMIN_MAIL_USERS.getPage());
		return model;
	}

	
	@RequestMapping(value="/editMailReponse")
	public ModelAndView editMailReponse(ModelAndView model,Long id){		
		
		Contact contact = contactRepository.findOne(id);
		
		model.addObject("feed", contact);
		model.addObject("source",contact.getSujet());
		
		model.setViewName(Views.VIEW_ADMIN_CREATION_CONTENU.getPage());
		return model;
	}
	
	@RequestMapping(value="/supprimerMail")
	public String supprimer(Long id,ModelAndView model){
		contactRepository.delete(id);
		return "redirect:contact";
	}

	@RequestMapping(value="/saveSource")
	public String saveSource(@ModelAttribute("source") String source){
		
		System.out.println(source);
		return "admin/creationContenu";
	}
}
