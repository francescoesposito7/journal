package com.spring.boot.journal.service;

import java.util.Collection;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.entities.NewsFeed;

public interface ISearchService {
	public Collection<NewsFeed> chercherNews(String q);
	public NewsFeed indexerNews(NewsFeed newsFeed);
	public void supprimerNewsParUpdatedDate(String updatedDate) throws Exception;
	
	public Collection<Cours> chercherCours(String q);
	public Cours indexerCours(Cours cours);
	public void supprimerCoursParUpdatedDate(String updatedDate) throws Exception;
	
}
