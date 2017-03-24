package com.spring.boot.journal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	public List<Image> findByName(String name);
		
}
