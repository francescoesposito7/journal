package com.spring.boot.journal.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repositoryES.CoursRepositoryES;
import com.spring.boot.journal.repositoryES.NewsRepositoryCustomES;
import com.spring.boot.journal.repositoryES.NewsRepositoryES;
import com.spring.boot.journal.service.ISearchService;

@Service
public class SearchService implements ISearchService {

	@Autowired
	private NewsRepositoryES newsRepositoryES;
	
	@Autowired
	private CoursRepositoryES coursRepositoryES;
	
	@Autowired
	private NewsRepositoryCustomES newsRepositoryCustomES;
	
	@Override
	public Collection<NewsFeed> chercherNews(String q) {
		return newsRepositoryES.findByContent(q);
	}

	@Override
	public NewsFeed indexerNews(NewsFeed newsFeed) {
		return newsRepositoryES.save(newsFeed);
	}

	@Override
	public void supprimerNewsParUpdatedDate(String updatedDate) throws Exception {
		List<NewsFeed> newsFeeds = newsRepositoryCustomES.findByDate(updatedDate);
		for (NewsFeed newsFeed : newsFeeds) {
			System.out.println("------------------>" + newsFeed.getTitle());
		}
		System.out.println("------------->" + newsFeeds.get(0).getUpdatedDate());
		if (newsFeeds.size() != 1) {
			throw new Exception("Index corrompu nb resultats : " + newsFeeds.size());
		} else {
				newsRepositoryES.delete(newsFeeds.get(0));
		}
	}

	@Override
	public Collection<Cours> chercherCours(String q) {
		Collection<Cours> cours = coursRepositoryES.findByContent(q);
		if (cours.isEmpty()) {
			cours = coursRepositoryES.findFuzzyByContent(q);
		}
		return cours;
	}

	@Override
	public Cours indexerCours(Cours cours) {
		return coursRepositoryES.save(cours);
	}

	@Override
	public void supprimerCoursParUpdatedDate(String updatedDate) throws Exception {
		Collection<Cours> cCours = coursRepositoryES.findByUpdatedDate(updatedDate);
		if (cCours.size() != 1) {
			throw new Exception("Index corrompu");
		} else {
			for (Cours cours : cCours) {
				coursRepositoryES.delete(cours);
			}
		}
	}
}
