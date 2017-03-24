package com.spring.boot.journal.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Tag implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String libelle;
	
	@ManyToMany(mappedBy="tags")
	private Collection<Cours> cours;

	public Tag() {
		super();
	}

	public Tag(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Long getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Collection<Cours> getCours() {
		return cours;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setCours(Collection<Cours> cours) {
		this.cours = cours;
	}
}
