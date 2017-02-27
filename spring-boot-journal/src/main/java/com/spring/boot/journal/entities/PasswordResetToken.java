package com.spring.boot.journal.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken {

	private static final int EXPIRATION = 60 * 24;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Utilisateur.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "USER_ID")
    private Utilisateur user;

    private Date dateExpiration;

    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(final String token) {
        super();

        this.token = token;
        this.dateExpiration = calculateExpiryDate(EXPIRATION);
    }

    public PasswordResetToken(final String token, final Utilisateur user) {
        super();

        this.token = token;
        this.user = user;
        this.dateExpiration = calculateExpiryDate(EXPIRATION);
    }

    //
    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(final Utilisateur user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return dateExpiration;
    }

    public void setExpiryDate(final Date expiryDate) {
        this.dateExpiration = expiryDate;
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
