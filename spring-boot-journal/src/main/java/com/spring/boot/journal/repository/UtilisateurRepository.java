package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
