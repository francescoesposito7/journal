package com.spring.boot.journal.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.Utilisateur;

@Repository("utilisateurRepository")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	public Utilisateur findByUsername(String username);
	public Utilisateur findByEmail(String email);
	
	/*
	@Modifying
	@Query("delete from utilisateur u where u.dateInscription<=?")
	public void deleteNotActive(Date now);*/
	
	/*@Query("select * from utilisateur u where (select addtime(u.dateInscription,'1 0:0:0')) <= ? and u.active=false")
	public boolean findInactive(Date now);*/

}
