package com.spring.boot.journal.service;

import java.util.Collection;

import com.spring.boot.journal.entities.NewsFeed;

public interface ISearchService {
	Collection<NewsFeed> chercherNews(String q);
	NewsFeed indexerNews(NewsFeed newsFeed);
	void supprimerNews(NewsFeed newsFeed);
}
