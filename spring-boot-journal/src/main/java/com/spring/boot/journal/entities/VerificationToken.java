package com.spring.boot.journal.entities;


import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {

	private static final int EXPIRATION = 60*24 ;
	
	@Id @GeneratedValue
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity=Utilisateur.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="USER_ID")
	private Utilisateur user;
	
	private Date dateExpiration;

	public VerificationToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VerificationToken(String token, Utilisateur user, Date dateExpiration) {
		super();
		this.token = token;
		this.user = user;
		this.dateExpiration = calculateExpiryDate(EXPIRATION);
	}
	
	public VerificationToken(String token, Utilisateur user) {
		super();
		this.token = token;
		this.user = user;
		this.dateExpiration = calculateExpiryDate(EXPIRATION);
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public Utilisateur getUser() {
		return user;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	
	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
	
	
}
