package com.spring.boot.journal.back.images.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.back.images.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	public List<Image> findByName(String name);
}
