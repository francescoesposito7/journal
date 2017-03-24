package com.spring.boot.journal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.Cours;
import com.spring.boot.journal.repository.CoursRepository;
import com.spring.boot.journal.service.CoursService;
import com.spring.boot.journal.service.ISearchService;
import com.spring.boot.journal.views.Views;

@Controller
@RequestMapping(value="/admin")
public class AdminCoursController {
	
	@Autowired
	private CoursRepository coursRepository;
	
	@Autowired
	private CoursService coursService;
	
	@Autowired
	private ISearchService searchService;
	
	//Affichage de la liste des toutes les news
		@RequestMapping(value="/listCours")
		public ModelAndView news(ModelAndView model,
							@RequestParam(name="page",defaultValue="0")int p){
			
			Page<Cours> news=coursRepository.findAll(new PageRequest(p, 10));

			int pageCount=news.getTotalPages();
			
			int pages[]=new int[pageCount];
			
			for(int i=0;i<pageCount;i++){pages[i]=i;}
			
			model.addObject("pages", pages);
			model.addObject("pageCours",news);
			model.addObject("pageCourante", p);
			model.setViewName(Views.VIEW_ADMIN_COURS.getPage());
			return model;
		}

		//Supprimer la news selectionée
		@RequestMapping(value="/supprimerCours")
		public String supprimerNews(Long id){
			searchService.supprimerCours(coursRepository.getOne(id));
			coursRepository.delete(id);
			return "redirect:listCours";
		}
		
		//Modification de la news selectionée
		@RequestMapping(value="/editCours")
		public ModelAndView editNews(ModelAndView model,Long id){		
			
			Cours cours = coursRepository.findOne(id);
			
			model.addObject("cours", cours);
			model.addObject("source",cours.getContent());
			
			model.setViewName(Views.VIEW_ADMIN_CREATION_CONTENU.getPage());
			return model;
		}
		
		@RequestMapping(value="/createCours")
		public ModelAndView createNews(ModelAndView model,
									   @ModelAttribute("source")String source,
									   @ModelAttribute("auteur")String author,
									   @ModelAttribute("titre")String title){		
			
			Cours cours = coursService.saveCours(source, author, title);
					
			model.addObject("cours", coursRepository.findOne(cours.getId()));
			
			model.setViewName(Views.VIEW_ADMIN_CREATION_CONTENU.getPage());
			
			return model;
		}
		
		@PostMapping(value="/saveCoursSource")
		public String saveSource(@ModelAttribute("source") String source,
								 Cours cours,
								 @ModelAttribute("saveAll")String j){

			
			if(j.equals("saveAll")){
				
				cours.setContent(source);
				searchService.indexerCours(cours);
				coursService.updateCoursSource(cours);
				return "redirect:listCours";
			}
			
			cours.setContent(source);
			searchService.indexerCours(cours);
			coursService.updateCoursSource(cours);
			return "redirect:editCours?id="+cours.getId();
		}
}
