package com.spring.boot.journal.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@SuppressWarnings("serial")
@Entity
public class Contact implements Serializable{

	@Id @GeneratedValue
	private Long id;
	
	@NotEmpty
	@NotNull
	@Size(max=30)
	private String nom;
	
	@NotEmpty
	@NotNull
	@Email
	private String email;
	
	@NotEmpty
	@NotNull
	private String objet;

	@NotEmpty
	@NotNull
	@Size(max=255)
	private String sujet;

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String nom, String email, String objet, String sujet) {
		super();
		this.nom = nom;
		this.email = email;
		this.objet = objet;
		this.sujet = sujet;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getEmail() {
		return email;
	}

	public String getObjet() {
		return objet;
	}

	public String getSujet() {
		return sujet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	
}
