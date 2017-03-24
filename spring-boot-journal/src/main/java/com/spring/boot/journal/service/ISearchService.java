package com.spring.boot.journal.service;

import java.util.Collection;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.entities.NewsFeed;

public interface ISearchService {
	public Collection<NewsFeed> chercherNews(String q);
	public NewsFeed indexerNews(NewsFeed newsFeed);
	public void supprimerNews(NewsFeed newsFeed);
	
	public Collection<Cours> chercherCours(String q);
	public Cours indexerCours(Cours cours);
	public void supprimerCours(Cours cours);
}
