package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.entities.ImageUser;

public interface ImageUserRepository extends JpaRepository<ImageUser, Long> {

	public ImageUser findByName(String name);
}
