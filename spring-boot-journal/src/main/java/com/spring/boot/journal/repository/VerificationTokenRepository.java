package com.spring.boot.journal.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

	public VerificationToken findByToken(String token);
	
	public VerificationToken findByUser(Utilisateur user);
	
	public void deleteByDateExpirationLessThan(Date now);
	
	/* @Modifying
	 @Query("delete from verification_token t where t.date_expiration <= ?1")
	 public void deleteAllExpiredSince(Date now);*/
}