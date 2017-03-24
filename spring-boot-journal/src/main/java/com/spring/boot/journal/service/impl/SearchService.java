package com.spring.boot.journal.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repositoryES.CoursRepositoryES;
import com.spring.boot.journal.repositoryES.NewsRepositoryES;
import com.spring.boot.journal.service.ISearchService;

@Service
public class SearchService implements ISearchService {

	@Autowired
	private NewsRepositoryES newsRepositoryES;
	
	@Autowired
	private CoursRepositoryES coursRepositoryES;
	
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

	@Override
	public Collection<Cours> chercherCours(String q) {
		Collection<Cours> cours = coursRepositoryES.findByContent(q);
		if (cours.isEmpty()) {
			System.out.println("*************************");
			cours = coursRepositoryES.findFuzzyByContent(q);
		}
		return cours;
	}

	@Override
	public Cours indexerCours(Cours cours) {
		return coursRepositoryES.save(cours);
	}

	@Override
	public void supprimerCours(Cours cours) {
		coursRepositoryES.delete(cours);
	}
}
