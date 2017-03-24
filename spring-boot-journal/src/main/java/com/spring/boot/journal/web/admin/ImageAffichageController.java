package com.spring.boot.journal.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.journal.entities.Image;
import com.spring.boot.journal.service.ImageService;


@Controller
@RequestMapping("/imgs")
public class ImageAffichageController {

	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Long itemId, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
	
		
		
	    Image picture = imageService.getOne(itemId);        
	    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    response.getOutputStream().write(picture.getData());
	
	
	    response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/imageMinDisplay", method = RequestMethod.GET)
	public void showMinImage(@RequestParam("id") Long itemId, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
	
	    Image picture = imageService.getOne(itemId);        
	    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    response.getOutputStream().write(picture.getMinData());
	
	
	    response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void showList(HttpServletResponse response) throws ServletException, IOException {
	
	    List<Image> pictures = imageService.findAll();
	    
	    for (Image image : pictures) {
			response.getOutputStream().print(image.getName() + "<img src='/imgs/imageMinDisplay?id=" + image.getId() + "' /><br />");
		}	
	
	    response.getOutputStream().close();
	}
}