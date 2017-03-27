package com.spring.boot.journal.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repository.NewsFeedRepository;
import com.spring.boot.journal.views.Views;

@Controller
public class NewsFeedController {
	
	@Autowired
	private NewsFeedRepository feedRepository;
	
	@GetMapping("/news")
	public ModelAndView listNews(ModelAndView modelAndView){
		
		List<NewsFeed> feeds = new ArrayList<NewsFeed>();		
		feeds = feedRepository.findAll();
		
		modelAndView.addObject("listNews", feeds);
		modelAndView.setViewName(Views.VIEW_NEWS.getPage());
		
		return modelAndView;
	}
	
	@GetMapping("/detailNews")
	public ModelAndView detailNews(ModelAndView modelAndView,Long id){

		NewsFeed feed = feedRepository.findOne(id);
		
		modelAndView.addObject("newsFeed", feed);
		modelAndView.setViewName(Views.VIEW_DETAIL_NEWS.getPage());
		
		return modelAndView;
	}
	
	@GetMapping("/listNewsCat")
	public ModelAndView listNewsCategory(ModelAndView modelAndView){
		
		List<NewsFeed> feeds = new ArrayList<NewsFeed>();		
		feeds = feedRepository.findAll();
		
		modelAndView.addObject("listNews", feeds);
		modelAndView.setViewName(Views.VIEW_NEWS.getPage());
		
		return modelAndView;
	}
}
