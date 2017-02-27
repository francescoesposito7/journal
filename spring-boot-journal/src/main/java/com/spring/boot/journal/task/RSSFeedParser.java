package com.spring.boot.journal.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.spring.boot.journal.entities.NewsFeed;
import com.spring.boot.journal.repository.NewsFeedRepository;

@Service
@Transactional
public class RSSFeedParser {
	
	@Value("${rss.feed}")
	private String url;

	@Autowired
	NewsFeedRepository feedRepository;
	
	@Scheduled(cron = "${purge.cron.feed}")
	public void parseFeed() throws IllegalArgumentException, MalformedURLException, FeedException, IOException{
	
		
		SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
		List<SyndEntry> entryList = feed.getEntries();

		if(feedRepository.findTopByOrderByUriDesc()==null){
		
			for (int i=0;i<entryList.size();i++){
							
				NewsFeed newsFeed = new NewsFeed();
				
				newsFeed.setAuthor(entryList.get(i).getAuthor());
				newsFeed.setLink(entryList.get(i).getLink());
				newsFeed.setUpdatedDate((entryList.get(i).getUpdatedDate().toString()));
				newsFeed.setTitle(entryList.get(i).getTitle());
				newsFeed.setContent(entryList.get(i).getContents().get(0).getValue().replace('"', '\''));
				newsFeed.setCategory(entryList.get(i).getCategories().get(0).getName());
				
				int uriId=Integer.valueOf(entryList.get(i).getUri().substring(25));
				
				newsFeed.setUri(uriId);
			
				feedRepository.save(newsFeed);			
			}
		}else{
			
			int oldUriFeed=feedRepository.findTopByOrderByUriDesc().getUri();
			
			for (int i=0;i<entryList.size();i++){
				
				NewsFeed newsFeed = new NewsFeed();
				
				newsFeed.setAuthor(entryList.get(i).getAuthor());
				newsFeed.setLink(entryList.get(i).getLink());
				newsFeed.setUpdatedDate((entryList.get(i).getUpdatedDate().toString()));
				newsFeed.setTitle(entryList.get(i).getTitle());
				newsFeed.setContent(entryList.get(i).getContents().get(0).getValue().replace('"', '\''));
				newsFeed.setCategory(entryList.get(i).getCategories().get(0).getName());
				
				int uriId=Integer.valueOf(entryList.get(i).getUri().substring(25));
				
				newsFeed.setUri(uriId);
			
				if(oldUriFeed<uriId ){
					feedRepository.save(newsFeed);
					System.out.println("***");
				}else
					if(oldUriFeed>uriId){
						break;
					}			
			}
		}
			
		
		
	}
	
	
	
}