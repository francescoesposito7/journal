package com.spring.boot.journal.repositoryES.impl;

import java.util.List;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.repositoryES.CoursRepositoryCustomES;

@Repository
public class CoursRepositoryCustom implements CoursRepositoryCustomES {

	@Autowired
	private ElasticsearchTemplate ElasticsearchTemplate;
	
	@Override
	public List<Cours> findByDate(String date) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
			.withQuery(new MatchQueryBuilder("updatedDate", date))
			.build();
		return ElasticsearchTemplate.queryForList(searchQuery, Cours.class);
	}

	
	@Override
	public List<Cours> findSecondChance(String q) {
		MatchQueryBuilder matchQuery = new MatchQueryBuilder("content", q);
		matchQuery.operator(Operator.OR);
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchQuery)
				.build();
		return ElasticsearchTemplate.queryForList(searchQuery, Cours.class);
	}
	
	@Override
	public List<Cours> findLastChance(String q) {
		MatchQueryBuilder matchQuery = new MatchQueryBuilder("content", q);
		matchQuery.fuzziness(1)
		.operator(Operator.OR);
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchQuery)
				.build();
		return ElasticsearchTemplate.queryForList(searchQuery, Cours.class);
	}
}
