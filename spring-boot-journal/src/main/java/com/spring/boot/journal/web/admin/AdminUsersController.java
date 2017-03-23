package com.spring.boot.journal.web.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.views.Views;

@Controller
@RequestMapping(value="/admin")
public class AdminUsersController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping(value="/listUsers")
	public ModelAndView Index(ModelAndView model,
						@RequestParam(name="page",defaultValue="0")int p,
						@RequestParam(name="motCle",defaultValue="")String mc){
		
		Page<Utilisateur> etds=utilisateurRepository.chercherUser(
				"%"+mc+"%", new PageRequest(p, 3));
		
		
		int pageCount=etds.getTotalPages();
		
		int pages[]=new int[pageCount];
		
		for(int i=0;i<pageCount;i++){pages[i]=i;}
		
		model.addObject("pages", pages);
		model.addObject("users",etds);
		model.addObject("pageCourante", p);
		model.addObject("motCle", mc);
		
		model.setViewName(Views.VIEW_ADMIN_USERS.getPage());
		
		return model;
	}
	
	
	@RequestMapping(value="/editUser")
	public ModelAndView edit(Long id,ModelAndView model){
		
		Utilisateur user=utilisateurRepository.getOne(id);
		
		model.addObject("user", user);
		model.setViewName(Views.VIEW_ADMIN_EDIT_USERS.getPage());
		
		return model;
	}
	
	@RequestMapping(value="/supprimerUser")
	public String supprimer(Long id){
		
		utilisateurRepository.delete(id);
		
		return "redirect:listUsers";
	}
	
	@PostMapping(value="/UpdateUser")
	public String update(@Valid Utilisateur et,
						BindingResult bindingResult/*,
						@RequestParam(name="picture") MultipartFile file*/) throws IllegalStateException, IOException{
		
		if(bindingResult.hasErrors()){
			return "adminEditUser";
		}
		
		/*if(!file.isEmpty()){
			et.setPhoto(file.getOriginalFilename());
		}*/
		
		utilisateurRepository.save(et);
		
		/*if(!file.isEmpty()){
			et.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+et.getId()));
		}
		*/
		
		return "redirect:listUsers";
	}
	
	
	
}
