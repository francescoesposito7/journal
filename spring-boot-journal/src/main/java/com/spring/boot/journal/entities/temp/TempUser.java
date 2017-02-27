package com.spring.boot.journal.entities.temp;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.spring.boot.journal.service.validator.ValidEmail;
import com.spring.boot.journal.service.validator.ValidPassword;


public class TempUser {

	@NotEmpty
	@NotNull
	@Size(max=30)
	private String nom;
	
	@NotEmpty
	@NotNull
	@Size(max=30)
	private String prenom;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;
	
	
	@NotEmpty
	@NotNull
	@Column(unique=true)
	@Size(max=30)
	private String username;
	
	@NotEmpty
	@NotNull
	@ValidEmail
	@Column(unique=true)
	@Size(max=30)
	private String email;
	
	@NotEmpty
	@NotNull
	@Size(min=4,max=20)
	@ValidPassword
	private String password;
	
	@NotEmpty
	@NotNull
	@Size(min=4)
	@ValidPassword
	private String passwordConfirmation;
	
	@Size(max=30)
	private String photo; 
	
	public TempUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public String getPhoto() {
		return photo;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setEmail(String email) {
		this.email = email; 
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordConfirmation(String passwordConf) {
		this.passwordConfirmation = passwordConf;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
