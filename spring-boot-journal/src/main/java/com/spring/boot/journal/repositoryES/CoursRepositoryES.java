package com.spring.boot.journal.repositoryES;

import java.util.Collection;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.boot.journal.entities.Cours;

public interface CoursRepositoryES extends ElasticsearchRepository<Cours, Long> {
	public Collection<Cours> findByContent(String content);

}
