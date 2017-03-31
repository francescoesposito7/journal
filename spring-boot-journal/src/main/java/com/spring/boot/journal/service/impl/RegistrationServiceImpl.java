package com.spring.boot.journal.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.service.RegistrationService;
import com.spring.boot.journal.service.UserService;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
    private UserService service;
	
	/*@Autowired
    private MessageSource messages;*/
	
	@Autowired
	 private JavaMailSender mailSender;
	 
	 @Autowired
	 private Environment environment;

	
	@Override
	public void confirmRegistration(Utilisateur user) {
		
		final String token = UUID.randomUUID().toString();
		service.createVerificationTokenForUser(user, token);
		
		final SimpleMailMessage email = constructEmailMessage(user, token);

        mailSender.send(email);
	}
	
	private final SimpleMailMessage constructEmailMessage(final Utilisateur user, final String token) {
        final String recipientAddress = user.getEmail();
        
        final String subject = "Registration Confirmation";
        
        final String confirmationUrl = "http://localhost:8080" + "/registrationConfirm?token=" + token;
        
        final String message = "You registered successfully. We will send you a confirmation message to your email account.";
        final SimpleMailMessage email = new SimpleMailMessage();
        
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }

	@Override
	public void resendPassword(Utilisateur user) {
		
		final String token = UUID.randomUUID().toString();
		service.createVerificationTokenForUser(user, token);
		
		final SimpleMailMessage email = resendMailPassword(user, token);
		mailSender.send(email);
	}
	
	private final SimpleMailMessage resendMailPassword(final Utilisateur user, final String token) {
        final String recipientAddress = user.getEmail();
        
        final String subject = "Registration Confirmation";
        
        final String confirmationUrl ="http://localhost:8080" + "/resetPassword?token=" + token;
        
        final String message = "Reset your password. Click on the link!";
        final SimpleMailMessage email = new SimpleMailMessage();
        
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(environment.getProperty("support.email"));
        
        return email;
    }

	@Override
	public void updatePassword(Utilisateur user) {
		// TODO Auto-generated method stub
		
	}

}
