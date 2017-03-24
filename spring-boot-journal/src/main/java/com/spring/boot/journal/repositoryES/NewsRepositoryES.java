package com.spring.boot.journal.repositoryES;

import java.util.Collection;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.boot.journal.entities.NewsFeed;

public interface NewsRepositoryES extends ElasticsearchRepository<NewsFeed, Long> {
	public Collection<NewsFeed> findByContent(String content);
}
