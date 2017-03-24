package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.entities.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {

}
