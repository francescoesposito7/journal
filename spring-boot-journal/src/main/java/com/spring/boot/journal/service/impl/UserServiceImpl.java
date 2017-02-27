package com.spring.boot.journal.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.boot.journal.entities.Role;
import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.RoleRepository;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UtilisateurRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Override
	public Utilisateur findUserbyUsername (String username) {
		return userRepository.findByUsername(username);

	}

	@Override
	public void saveUser(Utilisateur user,int type) {
		
		//Definition role utilisateur
		
		Role role = roleRepository.findOne((long) type);
		
		role.setUtilisateurs(new ArrayList<Utilisateur>());
		role.getUtilisateurs().add(user);
		user.setRole(new ArrayList<Role>());
		user.getRole().add(role);
		user.setActive(true);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		userRepository.save(user);
	}

	@Override
	public Utilisateur findUserbyEmail(String email) {
		return userRepository.findByEmail(email);

	}
}
