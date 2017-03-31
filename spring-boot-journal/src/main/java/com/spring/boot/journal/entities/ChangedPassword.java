package com.spring.boot.journal.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.spring.boot.journal.service.validator.ValidPassword;


public class ChangedPassword {

	/*@Id @GeneratedValue
	private Long id;*/
	
	@NotEmpty
	@NotNull
	@Size(min=4)
	@ValidPassword
	private String password;
	
	@NotEmpty
	@NotNull
	@Size(min=4)
	@ValidPassword
	private String confirmationPassword;

	public ChangedPassword(){}

	public String getPassword() {
		return password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
	
	
}
