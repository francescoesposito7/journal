package com.spring.boot.journal.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.service.ISearchService;
import com.spring.boot.journal.views.Views;

@Controller
public class SearchController {
	
	@Autowired
	ISearchService searchService;
	
	@RequestMapping("search")
	public ModelAndView search(@RequestParam(name="q")String q) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Views.VIEW_SEARCH.getPage());
		//TODO : Implémenter la recherche pour les articles, lorsqu'ils seront en place
		Collection<NewsFeed> newsFeeds = searchService.chercherNews(q);
		
		modelAndView.addObject("q", q);
		modelAndView.addObject("news", newsFeeds);
		return modelAndView;
	}
}
