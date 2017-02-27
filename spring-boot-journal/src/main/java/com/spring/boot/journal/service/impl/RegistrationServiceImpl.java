package com.spring.boot.journal.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
	
	@Autowired
    private MessageSource messages;
	
	/* @Autowired
	 private JavaMailSender mailSender;*/
	 
	 @Autowired
	 private Environment environment;

	
	@Override
	public void confirmRegistration(Utilisateur user) {
		
		final String token = UUID.randomUUID().toString();
		service.createVerificationTokenForUser(user, token);
		
		final SimpleMailMessage email = constructEmailMessage(/*event,*/ user, token);
		System.out.println("----> EMAIL : "+email);
        //mailSender.send(email);
	}
	
	private final SimpleMailMessage constructEmailMessage(/*final OnRegistrationCompleteEvent event,*/ final Utilisateur user, final String token) {
        final String recipientAddress = user.getEmail();
        
        final String subject = "Registration Confirmation";
        
        final String confirmationUrl = /*"------------------>"event.getAppUrl()*/"http://localhost:8080" + "/registrationConfirm?token=" + token;
        System.out.println("----> CONFIRMATION URL : "+confirmationUrl);
        
       // final String message = messages.getMessage("You registered successfully. We will send you a confirmation message to your email account.", null,null/*, event.getLocale()*/);
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
		
		final SimpleMailMessage email = resendMailPassword(/*event,*/ user, token);
		System.out.println("----> EMAIL : "+email);		
	}
	
	private final SimpleMailMessage resendMailPassword(/*final OnRegistrationCompleteEvent event,*/ final Utilisateur user, final String token) {
        final String recipientAddress = user.getEmail();
        
        final String subject = "Registration Confirmation";
        
        final String confirmationUrl = /*"------------------>"event.getAppUrl()*/"http://localhost:8080" + "/resendPassword?token=" + token;
        System.out.println("----> CONFIRMATION URL : "+confirmationUrl);
        
       // final String message = messages.getMessage("You registered successfully. We will send you a confirmation message to your email account.", null,null/*, event.getLocale()*/);
        final String message = "Reset your password. Click on the link!";
        final SimpleMailMessage email = new SimpleMailMessage();
        
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }

}
