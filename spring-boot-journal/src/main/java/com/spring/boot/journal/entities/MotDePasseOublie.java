package com.spring.boot.journal.entities;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.spring.boot.journal.service.validator.ValidEmail;

public class MotDePasseOublie {


	@NotEmpty(message="Nom d'utilisateur vide. Veuillez inserir un username")
	private String username;
	
	@ValidEmail
	private String email;
	
	public MotDePasseOublie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MotDePasseOublie(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
