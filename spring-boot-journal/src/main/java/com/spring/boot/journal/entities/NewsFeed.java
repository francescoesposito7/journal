package com.spring.boot.journal.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@SuppressWarnings("serial")
@Entity
@Document(indexName = "newsfeed", type = "newsfeed")
public class NewsFeed implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	private String title;
	private String link;
	private String category;
	private String author;
	private String id_Feed;
	private String updatedDate;
	@Column(columnDefinition="TEXT")
	@Field(type=FieldType.Attachment, index=FieldIndex.analyzed, analyzer="french", searchAnalyzer="french")
	private String content;
	private int uri;
	
	public NewsFeed() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsFeed(String title, String category, String link, String author, String id_Feed, String updatedDate, String content, int Uri) {
		super();
		this.title = title;
		this.link = link;
		this.category = category;
		this.author = author;
		this.id_Feed = id_Feed;
		this.updatedDate = updatedDate;
		this.content = content;
		this.uri = Uri;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getLink() {
		return link;
	}

	public String getCategory() {
		return category;
	}

	public String getAuthor() {
		return author;
	}

	public String getId_Feed() {
		return id_Feed;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public String getContent() {
		return content;
	}
	public int getUri() {
		return uri;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setId_Feed(String id_Feed) {
		this.id_Feed = id_Feed;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUri(int uri) {
		this.uri = uri;
	}
	
	
}
