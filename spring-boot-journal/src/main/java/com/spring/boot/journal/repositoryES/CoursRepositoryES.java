package com.spring.boot.journal.repositoryES;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.boot.journal.entities.Cours;

public interface CoursRepositoryES extends ElasticsearchRepository<Cours, Long> {
	/**
	 * Recherche prioritaire : Termes exacts et inclusifs
	 * 
	 * @param String
	 * @return Collection
	 * */
	public List<Cours> findByContent(String content);
}
