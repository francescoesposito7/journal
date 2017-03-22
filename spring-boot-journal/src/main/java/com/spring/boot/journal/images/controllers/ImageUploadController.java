package com.spring.boot.journal.back.images.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.journal.back.images.entities.Image;
import com.spring.boot.journal.back.images.services.ImageService;

@Controller
public class ImageUploadController {

	@Autowired
    private ImageService imageService;

    @GetMapping("/admin/image")
    public String uploadPage() {
        return "/admin/images/uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = null;
		try {
			file = imageService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Gérer le message d'erreur
			e.printStackTrace();
		}
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/admin/uploadImage")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes) {
    	for (MultipartFile file : files) {
	        try {
				imageService.tempStore(file);
		        // Resize de l'image de base
				imageService.resize(file.getOriginalFilename(), false);
				// Resize pour la miniature
				imageService.resize(file.getOriginalFilename(), true);
		        // Sauvegarde de l'objet (à la fois image de base et miniature)
				imageService.save(file.getOriginalFilename());
				// Suppression des fichiers temporaires
		        imageService.tempDelete(file.getOriginalFilename());
		        
			} catch (IOException e) {
				// TODO Gérer ce message d'erreur
				e.printStackTrace();
			}
	
	        redirectAttributes.addFlashAttribute("message",
	                "You successfully uploaded " + file.getOriginalFilename() + "!");
    	}
        return "redirect:/admin/home";
    }


	@RequestMapping("/afficher")
	public String admin(Model model) {
		List<Image> pictureList = imageService.findAll();
		model.addAttribute("pictures", pictureList);
		return "afficheur";
	}
	
	@RequestMapping(value="listImages", method=RequestMethod.GET,
            produces="application/json")
	public @ResponseBody List<Image> pay(@RequestParam("image") String name) {
		List<Image> listImgs = imageService.findByName(name);
		return listImgs;
	}
}
