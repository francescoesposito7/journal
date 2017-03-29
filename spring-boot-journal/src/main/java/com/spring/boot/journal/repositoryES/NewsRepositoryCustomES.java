package com.spring.boot.journal.repositoryES;

import java.util.List;

import com.spring.boot.journal.entities.NewsFeed;

public interface NewsRepositoryCustomES {
	
	public List<NewsFeed> findByDate(String date);
	
	public List<NewsFeed> findLastChance(String q);

	public List<NewsFeed> findSecondChance(String q);

}
