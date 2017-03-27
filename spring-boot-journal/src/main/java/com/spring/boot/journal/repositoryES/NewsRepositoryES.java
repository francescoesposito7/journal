package com.spring.boot.journal.repositoryES;

import java.util.Collection;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.entities.NewsFeed;

public interface NewsRepositoryES extends ElasticsearchRepository<NewsFeed, Long> {
	/**
	 * Recherche prioritaire : Termes exacts et inclusifs
	 * 
	 * @param String
	 * @return Collection
	 * */
	public Collection<NewsFeed> findByContent(String content);
	
	/**
	 * Recherche secondaire : Fuzzy Query
	 * 
	 * @param String
	 * @return Collection
	 */
    @Query("{\"query\": {\"fuzzy\" : { \"content\" : \"?0\" }}}")
	public Collection<Cours> findFuzzyByContent(String content);
    
    @Query("{\"bool\": {\"must\": [{\"match\": {\"newsfeed.updateddate\": \"?0\"}}]}}")
	public List<NewsFeed> findByUpdatedDate(String updatedDate);
}
