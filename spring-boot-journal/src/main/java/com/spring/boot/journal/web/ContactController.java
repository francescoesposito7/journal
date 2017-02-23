package com.spring.boot.journal.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.boot.journal.entities.Contact;
import com.spring.boot.journal.service.ContactService;
import com.spring.boot.journal.views.Views;

@Controller
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@RequestMapping(value="/SendUsQuestions",method=RequestMethod.POST)
	public String sendQuestions(@Valid Contact contact,
								BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			return Views.VIEW_CONTACT.getPage();
		}else{
			contactService.saveQuestion(contact);
		}
		
		return "redirect:contactSuccess";
	}
}
