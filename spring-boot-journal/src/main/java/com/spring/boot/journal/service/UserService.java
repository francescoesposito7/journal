package com.spring.boot.journal.service;

import com.spring.boot.journal.entities.Utilisateur;

public interface UserService {

	public Utilisateur findUserbyEmail(String email);
	
	public Utilisateur findUserbyUsername(String username);
	
	public void saveUser(Utilisateur user);
	
	public void createVerificationTokenForUser(final Utilisateur user, final String token);
	
	public String validateVerificationToken(String token);
	
	public Utilisateur getUser(String verificationToken);
}
