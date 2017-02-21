package com.spring.boot.journal.service;

import com.spring.boot.journal.entities.Utilisateur;

public interface UserService {

	public Utilisateur findUserbyEmail(String email);
	public void saveUser(Utilisateur user);
}
