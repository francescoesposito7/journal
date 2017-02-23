package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.Utilisateur;

@Repository("utilisateurRepository")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	public Utilisateur findByUsername(String username);
	public Utilisateur findByEmail(String email);

}
