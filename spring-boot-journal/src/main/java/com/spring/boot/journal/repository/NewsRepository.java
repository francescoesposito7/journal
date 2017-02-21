package com.spring.boot.journal.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.spring.boot.journal.domain.JournalEntry;

public interface NewsRepository extends JpaRepository<JournalEntry, Long> {
			
	List<JournalEntry> findByCreatedAfter(@Param("after") @DateTimeFormat(iso = ISO.DATE) Date date);
			
	List<JournalEntry> findByCreatedBetween(@Param("after")  @DateTimeFormat(iso = ISO.DATE) Date after,
											@Param("before") @DateTimeFormat(iso = ISO.DATE) Date before);
			
	List<JournalEntry> findByTitleContaining(@Param("word") String word);
			
	List<JournalEntry> findBySummaryContaining(@Param("word") String word);
}
