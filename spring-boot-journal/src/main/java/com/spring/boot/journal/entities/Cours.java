package com.spring.boot.journal.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@SuppressWarnings("serial")
@Entity
@Document(indexName = "cours", type = "cours")
public class Cours implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String titre;
	private String updatedDate;
	private boolean active;
	private String auteur;
	
	@Column(columnDefinition="TEXT")
	@Field(type=FieldType.String, index=FieldIndex.analyzed, analyzer="french", searchAnalyzer="french")
	private String content;
	
	@OneToOne(targetEntity = CoursPdf.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "PDF_ID")
	private CoursPdf pdf;
	
	@ManyToMany
	private Collection<Tag> tags;
	
	@ManyToMany(mappedBy="cours")
	private List<Utilisateur> utilisateurs;

	public Cours() {
		super();
	}

	public Cours(String titre, String updatedDate, boolean active, String auteur, String content) {
		super();
		this.titre = titre;
		this.updatedDate = updatedDate;
		this.active = active;
		this.auteur = auteur;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public boolean isActive() {
		return active;
	}

	public String getAuteur() {
		return auteur;
	}

	public String getContent() {
		return content;
	}

	public CoursPdf getPdf() {
		return pdf;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPdf(CoursPdf pdf) {
		this.pdf = pdf;
	}

	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
}
