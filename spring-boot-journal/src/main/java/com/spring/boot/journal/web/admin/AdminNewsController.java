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

import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repository.NewsFeedRepository;
import com.spring.boot.journal.service.NewsService;
import com.spring.boot.journal.views.Views;

@Controller
@RequestMapping(value="/admin")
public class AdminNewsController {
	

	@Autowired
	private NewsFeedRepository feedRepository;
	
	@Autowired
	private NewsService newsService;
	
	//Affichage de la liste des toutes les news
	@RequestMapping(value="/listNews")
	public ModelAndView news(ModelAndView model,
						@RequestParam(name="page",defaultValue="0")int p){
		
		Page<NewsFeed> news=feedRepository.findAll(new PageRequest(p, 10));

		int pageCount=news.getTotalPages();
		
		int pages[]=new int[pageCount];
		
		for(int i=0;i<pageCount;i++){pages[i]=i;}
		
		model.addObject("pages", pages);
		model.addObject("pageNews",news);
		model.addObject("pageCourante", p);
		model.setViewName(Views.VIEW_ADMIN_NEWS.getPage());
		return model;
	}
	
	//Supprimer la news selectionée
	@RequestMapping(value="/supprimerNews")
	public String supprimerNews(Long id){
		feedRepository.delete(id);
		return "redirect:listNews";
	}

	//Modification de la news selectionée
	@RequestMapping(value="/editNews")
	public ModelAndView editNews(ModelAndView model,Long id){		
		
		NewsFeed feed = feedRepository.findOne(id);
		
		model.addObject("feed", feed);
		model.addObject("source",feed.getContent());
		
		model.setViewName(Views.VIEW_ADMIN_CREATION_CONTENU.getPage());
		return model;
	}
	
	@RequestMapping(value="/createNews")
	public ModelAndView createNews(ModelAndView model,
								   @ModelAttribute("source")String source,
								   @ModelAttribute("author")String author,
								   @ModelAttribute("category")String category,
								   @ModelAttribute("title")String title){		
		
		NewsFeed feed = newsService.saveNews(source, author, category, title);
				
		model.addObject("feed", feedRepository.findOne(feed.getId()));
		
		model.setViewName(Views.VIEW_ADMIN_CREATION_CONTENU.getPage());
		
		return model;
	}
	
	@RequestMapping(value="/saveNews")
	public String saveNews(NewsFeed feed){
		
		//newsService.saveNewsEdition(feed);

		return "admin/home";
	}
	
	@PostMapping(value="/saveNewsSource")
	public String saveSource(@ModelAttribute("source") String source,
							 NewsFeed feed,
							 @ModelAttribute("saveAll")String j){

		
		if(j.equals("saveAll")){
			
			feed.setContent(source);
			newsService.updateNewsSource(feed);
			return "redirect:listNews";
		}
		
		feed.setContent(source);
		newsService.updateNewsSource(feed);
		
		return "redirect:editNews?id="+feed.getId();
	}

}
