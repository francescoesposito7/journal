package com.spring.boot.journal.service;

import com.spring.boot.journal.entities.Utilisateur;

public interface RegistrationService {

	public void confirmRegistration(Utilisateur user);
	
	public void resendPassword(Utilisateur user);
	
	public void updatePassword(Utilisateur user);
}
