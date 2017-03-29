package com.spring.boot.journal.repositoryES.impl;

import java.util.List;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repositoryES.NewsRepositoryCustomES;

@Repository
public class NewsRepositoryCustom implements NewsRepositoryCustomES {

	@Autowired
	private ElasticsearchTemplate ElasticsearchTemplate;
	
	@Override
	public List<NewsFeed> findByDate(String date) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
			.withQuery(new MatchQueryBuilder("updatedDate", date))
			.build();
		return ElasticsearchTemplate.queryForList(searchQuery, NewsFeed.class);
	}

	@Override
	public List<NewsFeed> findSecondChance(String q) {
		MatchQueryBuilder matchQuery = new MatchQueryBuilder("content", q);
		matchQuery.operator(Operator.OR);
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchQuery)
				.build();
		return ElasticsearchTemplate.queryForList(searchQuery, NewsFeed.class);
	}
	
	@Override
	public List<NewsFeed> findLastChance(String q) {
		MatchQueryBuilder matchQuery = new MatchQueryBuilder("content", q);
		matchQuery.fuzziness(1)
		.operator(Operator.OR);
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchQuery)
				.build();
		return ElasticsearchTemplate.queryForList(searchQuery, NewsFeed.class);
	}

}
