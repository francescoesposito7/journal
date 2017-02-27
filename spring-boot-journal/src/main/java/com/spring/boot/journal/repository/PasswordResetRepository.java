package com.spring.boot.journal.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.journal.entities.PasswordResetToken;
import com.spring.boot.journal.entities.Utilisateur;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long>{

	public PasswordResetToken findByToken(String token);
	
	public PasswordResetToken findByUser(Utilisateur user);
	
	public void deleteByDateExpirationLessThan(Date now);
	
	/*@Modifying
    @Query("delete from password_reset_token t where t.date_expiration <= ?1")
	public void deleteAllExpiredSince(Date now);*/
	
}
