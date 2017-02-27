package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.NewsFeed;

@Repository("newsFeedRepository")
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long>{
	
	
}
