package com.spring.boot.journal.service;

import com.spring.boot.journal.entities.Utilisateur;

public interface UserService {

	public Utilisateur findUserbyEmail(String email);
	public Utilisateur findUserbyUsername(String username);
	public void saveUser(Utilisateur user);
}
