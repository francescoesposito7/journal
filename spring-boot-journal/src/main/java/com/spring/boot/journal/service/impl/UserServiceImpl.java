package com.spring.boot.journal.service.impl;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.boot.journal.entities.Role;
import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.RoleRepository;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UtilisateurRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Override
	public Utilisateur findUserbyEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(Utilisateur user) {
		
		//Definition role utilisateur
		//TODO changer le method se saisie role
		/*---->*/Role role = roleRepository.findOne((long) 1);
		
		role.setUtilisateurs(new ArrayList<Utilisateur>());
		role.getUtilisateurs().add(user);
		user.setRole(new ArrayList<Role>());
		user.getRole().add(role);
		
		user.setMotDePasse(bCryptPasswordEncoder.encode(user.getMotDePasse()));
		
		userRepository.save(user);
		
	}

}
