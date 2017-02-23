package com.spring.boot.journal.views;

public enum Views {
	
	VIEW_INDEX("index"),
	VIEW_ACCUEIL("acceuil"),
	VIEW_INSCRIPTION("inscription"),
	VIEW_NEWS("news"),
	VIEW_LOGIN("login"),
	VIEW_CONTACT("contact"),
	VIEW_INSCFORM("inscriptionForm");

	private String page;

	private Views(String page){
		this.page = page;
	}
	
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
}
