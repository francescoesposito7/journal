package com.spring.boot.journal.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class CoursPdf implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="PDF_ID")
	private Long id;
	
	@Lob
	private Byte[] pdf;

	public CoursPdf() {
		super();
	}

	public CoursPdf(Byte[] pdf) {
		super();
		this.pdf = pdf;
	}

	public Long getId() {
		return id;
	}

	public Byte[] getPdf() {
		return pdf;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPdf(Byte[] pdf) {
		this.pdf = pdf;
	}
}
