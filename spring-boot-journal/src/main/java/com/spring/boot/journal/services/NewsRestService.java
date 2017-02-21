package com.spring.boot.journal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.journal.domain.News;
import com.spring.boot.journal.repository.NewsRepository;


@RestController
public class NewsRestService {
	
	@Autowired
	NewsRepository repo;
	
	public List<News> listNews(){
		return repo.findAll();
	}
	
}
