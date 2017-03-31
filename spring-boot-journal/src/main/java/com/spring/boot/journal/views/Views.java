package com.spring.boot.journal.views;

public enum Views {
	
	VIEW_INDEX("index"),
	VIEW_ACCUEIL("acceuil"),
	VIEW_INSCRIPTION("inscription"),
	
	VIEW_SEARCH("search"),
	
	VIEW_BAD_USER("badUser"),

	VIEW_INSCRIPTION_SUCCESS("inscriptionSuccess"),
	VIEW_NEWS("news"),
	VIEW_DETAIL_NEWS("detailNews"),
	VIEW_LOGIN("login"),
	VIEW_MOT_DE_PASSE_OUBLIE("motDePasseOublie"),
	VIEW_MOT_DE_PASSE_SUCCESS("motDePasseSucc"),
	VIEW_MOT_DE_PASSE_RESET("motDePasseReset"),
	VIEW_COURS("cours"),
	VIEW_CONTACT("contact"),
	VIEW_CONTACT_SUCCESS("contactSuccess"),
	VIEW_INSCFORM("inscriptionForm"),
	VIEW_INSCFACEBOOK("facebook/inscriptionFacebook"),
	
	VIEW_ADMIN_HOME("admin/adminHome"),
	VIEW_ADMIN_NEWS("admin/adminNews"),
	VIEW_ADMIN_COURS("admin/adminCours"),
	VIEW_ADMIN_MAIL_USERS("admin/adminMailUsers"),
	VIEW_ADMIN_CREATION_CONTENU("admin/creationContenu"),
	VIEW_ADMIN_USERS("admin/adminUsers"),
	VIEW_ADMIN_EDIT_USERS("admin/adminEditUser"),
	
	VIEW_PROFIL("profil/profil"),
	VIEW_PROFIL_PARAM("profil/parametres"),
	VIEW_PROFIL_COURS("profil/mesCours"),
	VIEW_PROFIL_MESS("profil/mesMessages");
	
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
