package com.spring.boot.journal.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
public class Utilisateur implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
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
	@Email
	@Column(unique=true)
	@Size(max=30)
	private String email;
	
	@NotEmpty
	@NotNull
	@Size(min=4)
	private String password;
	
	@Size(max=30)
	private String photo; 
	
	// Creation TABLE utilisateur_roles 
	@ManyToMany
	private Collection<Role> roles;

	private boolean active;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(String nom, String prenom, Date dateNaissance, String username,String email, String password, String photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.email=email;
		this.password = password;
		this.photo = photo;
		this.setActive(false);
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public Long getId() {
		return id;
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

	public String getPhoto() {
		return photo;
	}
	

	public Collection<Role> getRole() {
		return roles;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setRole(Collection<Role> roles) {
		this.roles=roles;
	}
	
}
