package com.spring.boot.journal.views;

public enum Views {
	
	VIEW_INDEX("index"),
	VIEW_ACCUEIL("acceuil"),
	VIEW_INSCRIPTION("inscription"),
	VIEW_INSCRIPTION_SUCCESS("inscriptionSuccess"),
	VIEW_NEWS("news"),
	VIEW_LOGIN("login"),
	VIEW_CONTACT("contact"),
	VIEW_CONTACT_SUCCESS("contactSuccess"),
	VIEW_INSCFORM("inscriptionForm"),
	VIEW_ADMIN_HOME("admin/adminConsole");

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
