package com.spring.boot.journal.service.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repository.NewsFeedRepository;
import com.spring.boot.journal.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsFeedRepository feedRepository;
	
	@Override
	public NewsFeed saveNews(String content,String author,String category,String title) {
		
		NewsFeed feed = new NewsFeed();
		
		feed.setAuthor(author);
		feed.setCategory(category);
		feed.setContent(content);
		feed.setTitle(title);
		feed.setUpdatedDate(Date.from(Instant.now()).toString());
		feed.setUri(1);

		return feedRepository.save(feed);
	}


	@Override
	public NewsFeed updateNewsSource(NewsFeed feed){
		
		feed.setUpdatedDate(Date.from(Instant.now()).toString());
		return feedRepository.save(feed);
	}

}
