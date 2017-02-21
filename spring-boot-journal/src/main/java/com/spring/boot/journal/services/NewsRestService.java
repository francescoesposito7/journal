package com.spring.boot.journal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.journal.domain.JournalEntry;
import com.spring.boot.journal.repository.NewsRepository;

@RestController
public class NewsRestService {
	
	@Autowired
	NewsRepository repo;
	
	public List<JournalEntry> listNews(){
		return repo.findAll();
	}
	
}
