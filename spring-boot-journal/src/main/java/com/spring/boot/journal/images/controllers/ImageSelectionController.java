package com.spring.boot.journal.back.images.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.back.images.entities.Image;
import com.spring.boot.journal.back.images.services.ImageService;

@Controller
public class ImageSelectionController {

	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/tinymce/dialog")
	public ModelAndView modalImage(ModelAndView model){		
		
		// Récupérer les infos des photos
		List<Image> images = imageService.findAll();
		model.addObject("image", images);
		model.setViewName("admin/dialog");
		return model;
	}
}
