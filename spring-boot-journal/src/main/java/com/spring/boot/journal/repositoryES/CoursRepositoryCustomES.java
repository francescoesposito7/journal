package com.spring.boot.journal.repositoryES;

import java.util.List;

import com.spring.boot.journal.entities.Cours;

public interface CoursRepositoryCustomES {
	
	public List<Cours> findByDate(String date);

	List<Cours> findSecondChance(String q);

	List<Cours> findLastChance(String q);

}
