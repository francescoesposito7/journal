package com.spring.boot.journal.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Role implements Serializable{

	@Id @GeneratedValue
	@Column(name="ROLE_ID")
	private Long id;
	
	private String roles;
	
	// Creation TABLE utilisateur_roles 
	@ManyToMany(mappedBy="roles")
	private List<Utilisateur> utilisateurs;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String roles) {
		super();
		this.roles = roles;
		
	}

	public Long getId() {
		return id;
	}

	public String getRoles() {
		return roles;
	}
	
	@ManyToMany
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	

	
	
	
	
}
