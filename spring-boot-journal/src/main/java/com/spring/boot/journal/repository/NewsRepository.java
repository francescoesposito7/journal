package com.spring.boot.journal.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.spring.boot.journal.domain.News;


public interface NewsRepository extends JpaRepository<News, Long> {

			
	List<News> findByCreatedAfter(@Param("after") @DateTimeFormat(iso = ISO.DATE) Date date);
			
	List<News> findByCreatedBetween(@Param("after")  @DateTimeFormat(iso = ISO.DATE) Date after,
											@Param("before") @DateTimeFormat(iso = ISO.DATE) Date before);
			
	List<News> findByTitleContaining(@Param("word") String word);
			
	List<News> findBySummaryContaining(@Param("word") String word); 
}
