package com.spring.boot.journal.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.spring.boot.journal.entities.ImageUser;
import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.UtilisateurRepository;

@Controller
public class ImageController {

	@Autowired
	private UtilisateurRepository UtilisateurRepository;
	
	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Long userId, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
		
		Utilisateur user = UtilisateurRepository.getOne(userId);
		ImageUser picture = user.getPhoto();
	    
	    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    response.getOutputStream().write(picture.getData());
	
	    response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/imageMinDisplay", method = RequestMethod.GET)
	public void showMinImage(@RequestParam("id") Long userId, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
	
		Utilisateur user = UtilisateurRepository.getOne(userId);
		ImageUser picture = user.getPhoto();    
	    
	    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    response.getOutputStream().write(picture.getMinData());
	
	    response.getOutputStream().close();
	}
}
