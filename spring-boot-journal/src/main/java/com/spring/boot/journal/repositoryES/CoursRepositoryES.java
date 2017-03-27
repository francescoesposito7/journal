package com.spring.boot.journal.repositoryES;

import java.util.Collection;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.boot.journal.entities.Cours;

public interface CoursRepositoryES extends ElasticsearchRepository<Cours, Long> {
	/**
	 * Recherche prioritaire : Termes exacts et inclusifs
	 * 
	 * @param String
	 * @return Collection
	 * */
	public Collection<Cours> findByContent(String content);

	/**
	 * Recherche secondaire : Fuzzy Query
	 * 
	 * @param String
	 * @return Collection
	 */
    @Query("{\"query\": {\"fuzzy\" : { \"content\" : \"?0\" }}}")
	public Collection<Cours> findFuzzyByContent(String content);
    
    public Collection<Cours> findByUpdatedDate(String updatedDate);
}
