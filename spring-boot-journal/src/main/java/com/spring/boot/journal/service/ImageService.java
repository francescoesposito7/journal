package com.spring.boot.journal.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.journal.entities.Image;
import com.spring.boot.journal.entities.ImageUser;

public interface ImageService {
	void save(String path) throws IOException;
	List<Image> findAll();
	List<Image> findByName(String name);
	Image getOne(Long id);
	void tempStore(MultipartFile file) throws IOException;
	void resize(String path, boolean isMiniature) throws IOException;
	Resource load(String filename) throws MalformedURLException;
	void tempDelete(String filename) throws IOException;
	
	
	ImageUser findImgByName(String name);
	ImageUser savePhotoUser(String path) throws IOException;
	ImageUser getUserOne(Long id);
	
}
