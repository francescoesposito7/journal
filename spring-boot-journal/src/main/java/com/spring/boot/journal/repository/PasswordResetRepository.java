package com.spring.boot.journal.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.PasswordResetToken;
import com.spring.boot.journal.entities.Utilisateur;

@Repository("passwordResetRepository")
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long>{

	public PasswordResetToken findByToken(String token);
	
	public PasswordResetToken findByUser(Utilisateur user);
	
	public void deleteByDateExpirationLessThan(Date now);
	
	@Modifying
    @Query("delete from PasswordResetToken t where t.dateExpiration<=?")
	void deleteAllExpiredSince(Date now);
	
}
