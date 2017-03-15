package com.spring.boot.journal.service;

import com.spring.boot.journal.entities.NewsFeed;

public interface NewsService {

	public NewsFeed saveNews(String content,String author,String category,String title);

	public void updateNewsSource(NewsFeed feed);
}
