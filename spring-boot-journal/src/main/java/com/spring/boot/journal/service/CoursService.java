package com.spring.boot.journal.service;

import com.spring.boot.journal.entities.Cours;

public interface CoursService {
	public Cours saveCours(String content, String auteur, String titre);
	public Cours updateCoursSource(Cours cours);

}
