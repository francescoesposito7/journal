package com.spring.boot.journal.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.journal.back.images.services.ImageService;
import com.spring.boot.journal.entities.ImageUser;
import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.RoleRepository;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.service.RegistrationService;
import com.spring.boot.journal.service.UserService;


@Controller
public class UtilisateurController {
	
	@Autowired
	UtilisateurRepository repository;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RegistrationService regService;
	
	@Value("${dir.images}")
	private String imageDir;
	
	@Autowired
	private ImageService imageService;

	
	@RequestMapping(value="/SaveUtilisateur",method=RequestMethod.POST)
	public String saveUtilisateur( @Valid Utilisateur user,
									BindingResult bindingResult,
									@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException, ParseException{		

		
		//Control si l'username est dejà utilisé
		if(userService.findUserbyUsername(user.getUsername()) != null){
			bindingResult
			.rejectValue("username", "username.user",
					"Nom d'utilisateur déjà utilisé. Veuillez utiliser un autre nom");		
		}
		
		//Control si la mail est dejà utilisé
		if ( userService.findUserbyEmail(user.getEmail()) != null){
			bindingResult
			.rejectValue("email", "email.user",
				"Email dejà utilisé. Veuillez utiliser un autre email");
		}
		
		
		if(!user.getPassword().equals(user.getPasswordConfirmation())){
			bindingResult
			.rejectValue("passwordConfirmation", "passwordConfirmation.user",
				"Password doesn't match!");
		}
		
		//Si il y a des error dans le formularie on reste la
		if(bindingResult.hasErrors()){
			return "inscriptionForm";
		
		}else{
			
			ImageUser image = new ImageUser();
			
			try {
				imageService.tempStore(file);
		        // Resize de l'image de base
				imageService.resize(file.getOriginalFilename(), false);
				// Resize pour la miniature
				imageService.resize(file.getOriginalFilename(), true);
		        // Sauvegarde de l'objet (à la fois image de base et miniature)
				image = imageService.savePhotoUser(file.getOriginalFilename());
				// Suppression des fichiers temporaires
		        imageService.tempDelete(file.getOriginalFilename());
		        
		        user.setPhoto(image);
		        
			} catch (IOException e) {
				// TODO Gérer ce message d'erreur
				e.printStackTrace();
			}
			
			
			
			userService.saveUser(user);
			regService.confirmRegistration(user);
			
			
		}
	
		return "redirect:iscriptionSuccess";
	}
	
	@RequestMapping(value="/SaveUtilisateurFacebook",method=RequestMethod.POST)
	public String saveUtilisateurFacebook( @Valid Utilisateur user,
									BindingResult bindingResult,
									@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException, ParseException{		

		
		//Control si l'username est dejà utilisé
		if(userService.findUserbyUsername(user.getUsername()) != null){
			bindingResult
			.rejectValue("username", "username.user",
					"Nom d'utilisateur déjà utilisé. Veuillez utiliser un autre nom");		
		}
		
		//Control si la mail est dejà utilisé
		if ( userService.findUserbyEmail(user.getEmail()) != null){
			bindingResult
			.rejectValue("email", "email.user",
				"Email dejà utilisé. Veuillez utiliser un autre email");
		}
		
		//Si il y a des error dans le formularie on reste la
		if(bindingResult.hasErrors()){
			return "inscriptionFacebook";
		
		}else{
			
			ImageUser image = new ImageUser();
			
			try {
				imageService.tempStore(file);
		        // Resize de l'image de base
				imageService.resize(file.getOriginalFilename(), false);
				// Resize pour la miniature
				imageService.resize(file.getOriginalFilename(), true);
		        // Sauvegarde de l'objet (à la fois image de base et miniature)
				image = imageService.savePhotoUser(file.getOriginalFilename());
				// Suppression des fichiers temporaires
		        imageService.tempDelete(file.getOriginalFilename());
		        user.setPhoto(image);
			} catch (IOException e) {
				// TODO Gérer ce message d'erreur
				e.printStackTrace();
			}
			
			
			
			userService.saveUser(user);
			regService.confirmRegistration(user);
		}
		
		return "redirect:iscriptionSuccess";
	}
}
