package com.spring.boot.journal.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.RoleRepository;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.service.UserService;


@Controller
public class UtilisateurController {
	
	@Autowired
	UtilisateurRepository repository;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	private UserService userService;
	
	@Value("${dir.images}")
	private String imageDir;

	
	@RequestMapping(value="/SaveUtilisateur",method=RequestMethod.POST)
	public String saveUtilisateur( @Valid Utilisateur user,
									BindingResult bindingResult,
									@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException, ParseException{
		
		Utilisateur userExists = userService.findUserbyUsername(user.getUsername());
		
		
		//Control si la mail est dejà utilisé
		if(userService.findUserbyUsername(user.getUsername()) != null){
			bindingResult
			.rejectValue("username", "username.user",
					"Username dejà enregistré. S'il vous plait inserer un username valide!");
		}else if( userService.findUserbyEmail(user.getEmail()) != null){
			bindingResult
			.rejectValue("email", "error.user",
					"Email dejà enregistré. S'il vous plait inserer une email valide!");
		}
		
		//Si il y a des error dans le formularie on reste la
		if(bindingResult.hasErrors()){
			return "inscriptionForm";
		}else{
			userService.saveUser(user);
		}
		
		//Creation dossier pour les images des tous les utilisateur 
		File f = new File(imageDir);
		boolean exist = f.isDirectory();
		
		if(!exist){
			new File(imageDir).mkdirs();
		}
		
		if(!file.isEmpty()){
			user.setPhoto(file.getOriginalFilename());
		}
		
		if(!file.isEmpty()){
			user.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+user.getId()));
		}
		
		return "redirect:acceuil";
	}
	
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody //ritorna un dato binario quindi differente dalle altre risposte
	public byte[] getPhoto(Long id) throws FileNotFoundException, IOException{
		
		File file = new File(imageDir+id);
		
		return IOUtils.toByteArray(new FileInputStream(file));
	}

}
