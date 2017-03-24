package com.spring.boot.journal.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repositoryES.NewsRepositoryES;
import com.spring.boot.journal.service.ISearchService;

@Service
public class SearchService implements ISearchService {

	@Autowired
	NewsRepositoryES newsRepositoryES;
	
	@Override
	public Collection<NewsFeed> chercherNews(String q) {
		return newsRepositoryES.findByContent(q);
	}

	@Override
	public NewsFeed indexerNews(NewsFeed newsFeed) {
		return newsRepositoryES.save(newsFeed);
	}

	@Override
	public void supprimerNews(NewsFeed newsFeed) {
		newsRepositoryES.delete(newsFeed);		
	}
}
