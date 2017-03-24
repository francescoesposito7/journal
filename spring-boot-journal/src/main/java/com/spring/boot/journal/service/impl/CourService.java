package com.spring.boot.journal.service.impl;

import java.time.Instant;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.repository.CoursRepository;
import com.spring.boot.journal.service.CoursService;

@Service
@Transactional
public class CourService implements CoursService {

	@Autowired
	private CoursRepository coursRepository;
	
	@Override
	public Cours saveCours(String content, String auteur, String titre) {
		Cours cours = new Cours();
		
		cours.setAuteur(auteur);
		cours.setContent(content);
		cours.setTitre(titre);
		cours.setUpdatedDate(Date.from(Instant.now()).toString());

		return coursRepository.save(cours);
	}

	@Override
	public Cours updateCoursSource(Cours cours) {
		cours.setUpdatedDate(Date.from(Instant.now()).toString());
		return coursRepository.save(cours);
	}
}
