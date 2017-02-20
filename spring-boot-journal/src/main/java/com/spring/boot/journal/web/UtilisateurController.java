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
import com.spring.boot.journal.repository.UtilisateurRepository;


@Controller
public class UtilisateurController {
	
	@Autowired
	UtilisateurRepository repository;
	
	@Value("${dir.images}")
	private String imageDir;

	
	@RequestMapping(value="/SaveUtilisateur",method=RequestMethod.POST)
	public String save(@Valid Utilisateur user,
						BindingResult bindingResult,
						@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException, ParseException{
		
		if(bindingResult.hasErrors()){
			return "inscriptionForm";
		}
		
		File f = new File(imageDir);
		boolean exist = f.isDirectory();
		
		if(!exist){
			new File(imageDir).mkdirs();
		}
		
		if(!file.isEmpty()){
			user.setPhoto(file.getOriginalFilename());
		}
		
		user.setActive(true);
		
		repository.save(user);
		
		if(!file.isEmpty()){
			user.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+user.getId()));
		}
		
		return "redirect:accueil";
	}
	
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody //ritorna un dato binario quindi differente dalle altre risposte
	public byte[] getPhoto(Long id) throws FileNotFoundException, IOException{
		
		File file = new File(imageDir+id);
		
		return IOUtils.toByteArray(new FileInputStream(file));
	}

}
