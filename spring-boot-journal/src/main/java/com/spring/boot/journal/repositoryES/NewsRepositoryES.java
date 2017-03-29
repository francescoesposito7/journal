package com.spring.boot.journal.repositoryES;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.boot.journal.entities.NewsFeed;

public interface NewsRepositoryES extends ElasticsearchRepository<NewsFeed, Long> {
	/**
	 * Recherche prioritaire : Termes exacts et inclusifs
	 * 
	 * @param String
	 * @return Collection
	 * */
	public List<NewsFeed> findByContent(String content);
}
