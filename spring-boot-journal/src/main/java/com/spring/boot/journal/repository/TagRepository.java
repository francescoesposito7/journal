package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
