package com.spring.boot.journal.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.spring.boot.journal.service.validator.ValidEmail;
import com.spring.boot.journal.service.validator.ValidPassword;

@SuppressWarnings("serial")
@Entity
public class Utilisateur implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	private Long facebookId;
	
/*	@NotEmpty
	@NotNull*/
	@Size(max=30)
	private String nom;
	
/*	@NotEmpty
	@NotNull*/
	@Size(max=30)
	private String prenom;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateInscription;
	
	
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
	@Size(min=4)
	@ValidPassword
	private String password;
	
	@NotEmpty
	@NotNull
	@Size(min=4)
	@ValidPassword
	private String passwordConfirmation;
	
	private int typeCompte;
	
	
	
	@OneToOne(targetEntity = ImageUser.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "PHOTO_USER_ID")
	private ImageUser photo;
	
	// Creation TABLE utilisateur_roles 
	@ManyToMany
	private Collection<Role> roles;

	private boolean active;
	
	private boolean newsletter;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(String nom, String prenom, Date dateNaissance, String username,String email, String password,String passwordConfirm,int typeCompte, ImageUser photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.email=email;
		this.password = password;
		this.passwordConfirmation = passwordConfirm;
		this.typeCompte=typeCompte;
		this.photo = photo;
		this.setActive(false);
		this.newsletter=false;
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
	
	public Date getDateInscription() {
		return dateInscription;
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
	
	public int getTypeCompte(){
		return typeCompte;
	}

	public ImageUser getPhoto() {
		return photo;
	}
	

	public Collection<Role> getRole() {
		return roles;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean isNewsletter() {
		return newsletter;
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
	
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
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

	public void setPhoto(ImageUser photo1) {
		this.photo = photo1;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setRole(Collection<Role> roles) {
		this.roles=roles;
	}
	
	public void setTypeCompte(int typeCompte){
		this.typeCompte=typeCompte;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public Long getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(Long facebookId) {
		this.facebookId = facebookId;
	}

}
