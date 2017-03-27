package com.spring.boot.journal.repositoryES;

import java.util.List;

import com.spring.boot.journal.entities.NewsFeed;

public interface NewsRepositoryCustomES {
	
	public List<NewsFeed> findByDate(String date);

}
