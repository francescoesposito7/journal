package com.spring.boot.journal.service;

import java.util.List;

import com.spring.boot.journal.entities.Contact;

public interface ContactService {

	public void saveQuestion(Contact contact);
	public List<Contact> listQuestions ();
}
