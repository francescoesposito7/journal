package com.spring.boot.journal.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class Image implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="PHOTO_ID")
	private Long id;
	private String name;
	@Lob
	private byte[] data;
	@Lob
	private byte[] minData;
	
	public Image() {
		super();
	}

	public Image(String name, byte[] data, byte[] minData) {
		super();
		this.name = name;
		this.data = data;
		this.minData = minData;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public byte[] getData() {
		return data;
	}

	public byte[] getMinData() {
		return minData;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setMinData(byte[] minData) {
		this.minData = minData;
	}
}
