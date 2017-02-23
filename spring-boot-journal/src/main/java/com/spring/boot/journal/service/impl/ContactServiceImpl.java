package com.spring.boot.journal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.Contact;
import com.spring.boot.journal.repository.ContactRepository;
import com.spring.boot.journal.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public void saveQuestion(Contact contact) {

		contactRepository.save(contact);
	}

	@Override
	public List<Contact> listQuestions() {

		return contactRepository.findAll();
	}

}
