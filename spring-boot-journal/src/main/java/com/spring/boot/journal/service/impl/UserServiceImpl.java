package com.spring.boot.journal.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.Role;
import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.entities.VerificationToken;
import com.spring.boot.journal.repository.RoleRepository;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.repository.VerificationTokenRepository;
import com.spring.boot.journal.service.RegistrationService;
import com.spring.boot.journal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public static final String TOKEN_VALID = "valid";
	public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
	
	@Autowired
	private UtilisateurRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private RegistrationService regService;
    


	@Override
	public void saveUser(Utilisateur user) {

		
		//Definition role utilisateur
		
		Role role = roleRepository.findOne((long) user.getTypeCompte());
		
		role.setUtilisateurs(new ArrayList<Utilisateur>());
		role.getUtilisateurs().add(user);
		user.setRole(new ArrayList<Role>());
		user.getRole().add(role);
		user.setActive(false);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);		
	}
	

	@Override
	public Utilisateur findUserbyEmail(String email) {
		return userRepository.findByEmail(email);

	}
	
	@Override
	public Utilisateur findUserbyUsername (String username) {
		return userRepository.findByUsername(username);

	}

	@Override
	public void createVerificationTokenForUser(Utilisateur user, String token) {

		final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
	}
	
	@Override
    public String validateVerificationToken(String token) {
        
		final VerificationToken verificationToken = tokenRepository.findByToken(token);
        
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final Utilisateur user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        
        if ((verificationToken.getDateExpiration().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

        user.setActive(true);
        userRepository.save(user);
        
        return TOKEN_VALID;
    }


	@Override
	public Utilisateur getUser(String verificationToken) {
		 
		final VerificationToken token = tokenRepository.findByToken(verificationToken);
	        if (token != null) {
	            return token.getUser();
	        }
	    
	        return null;
	}


}
