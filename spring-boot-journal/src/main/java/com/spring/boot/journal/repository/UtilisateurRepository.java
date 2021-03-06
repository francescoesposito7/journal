package com.spring.boot.journal.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.Utilisateur;

@Repository("utilisateurRepository")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	public Utilisateur findByUsername(String username);
	public Utilisateur findByFacebookId(Long facebookId);
	public Utilisateur findByEmail(String email);

	@Query("select u from Utilisateur u where u.dateInscription <= ?  and u.active=false")
	public List<Utilisateur> findInactive(Date now);
	
	@Query("select u from Utilisateur u where u.username like :x")
	public Page<Utilisateur> chercherUser(@Param("x") String mc,Pageable pageable);

}
