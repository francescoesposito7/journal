package com.spring.boot.journal.back.images.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.journal.back.images.entities.Image;
import com.spring.boot.journal.back.images.repositories.ImageRepository;
import com.spring.boot.journal.entities.ImageUser;
import com.spring.boot.journal.repository.ImageUserRepository;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Value("${dir.temp}")
	private Path tempLocation;
	@Value("${dir.temp.min}")
	private Path tempMinLocation;
	@Value("${file.maxsize}")
	private int maxSize;
	@Value("${file.min.maxsize}")
	private int minMaxSize;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ImageUserRepository imgUsrRepository;
	
	@Override
    public Resource load(String filename) throws MalformedURLException {
		Path path = tempLocation.resolve(filename);
		Resource resource;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			throw e;
		}
		return resource;
	}
	
	@Override
	public void save(String path) throws IOException {

		File file = new File(path);
		String filename = file.getName();
		
		Path realPath = tempLocation.resolve(path);
		Path realPathMin = tempMinLocation.resolve(path);
		byte[] data = null;
		byte[] dataMin = null;
		try {
			data = Files.readAllBytes(realPath);
			dataMin = Files.readAllBytes(realPathMin);
		} catch (IOException e) {
			throw e;
		}
		
		Image picture = new Image(filename, data, dataMin);
		imageRepository.save(picture);
	}

	@Override
	public List<Image> findAll() {
		return imageRepository.findAll();
	}
	
	@Override
	public List<Image> findByName(String name) {
		return imageRepository.findByName(name);
	}
	
	@Override
	public Image getOne(Long id) {
		Image picture = imageRepository.getOne(id);
		Hibernate.initialize(picture.getData());
		return picture;
	}
	
	@Override
    public void tempStore(MultipartFile file) throws IOException {
		Path path = this.tempLocation.resolve(file.getOriginalFilename());
        try {
    		if (Files.exists(tempLocation) != true) {
    			Files.createDirectories(tempLocation);
    		}
    		if (Files.exists(tempMinLocation) != true) {
    			Files.createDirectories(tempMinLocation);
    		}
			Files.copy(file.getInputStream(), path);
		} catch (IOException e) {
			throw e;
		}
    }
	
	@Override
	public void tempDelete(String filename) throws IOException {
		Path realPath = tempLocation.resolve(filename);
		Path realPathMin = tempMinLocation.resolve(filename);
		try {
			Files.deleteIfExists(realPath);
			Files.deleteIfExists(realPathMin);
		} catch (IOException e) {
			throw e;
		}
	}
	
	@Override
	public void resize(String path, boolean isMiniature) throws IOException {
		File file = tempLocation.resolve(path).toFile();
		BufferedImage bufferedImage = null;
		
		try {
			bufferedImage = ImageIO.read(file);
			float startHeight = bufferedImage.getHeight();
			float startWidth = bufferedImage.getWidth();
			
			// Calculer la nouvelle hauteur et largeur
			float ratio = 0;
			float max = 0;
			if (isMiniature) {
				max = this.minMaxSize;
			} else {
				max = this.maxSize;
			}
				
			if (startWidth > startHeight) {
				ratio = max / startWidth;
			} else {
				ratio = max / startHeight;
			}

			int newHeight = (int) (ratio * startHeight);
			int newWidth = (int) (ratio * startWidth);
			
			// Créer la nouvelle image
			BufferedImage outputImage = new BufferedImage(newWidth, newHeight, bufferedImage.getType());
			
			// Formater la nouvelle image dans le canvas
			Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
	        g2d.dispose();
	        
	        // Extraction de l'extension et écriture du fichier de sortie après suppression du fichier de départ
	        String formatName = path.substring(path.lastIndexOf(".") + 1);
	        
	        if (isMiniature) {
	        	ImageIO.write(outputImage, formatName, new File(tempMinLocation.resolve(path).toString()));
	        } else {	        	
	        	ImageIO.write(outputImage, formatName, new File(tempLocation.resolve(path).toString()));
	        }
			
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public ImageUser findImgByName(String name) {
		
		return imgUsrRepository.findByName(name);
	}

	@Override
	public ImageUser savePhotoUser(String path) throws IOException {
		
		File file = new File(path);
		String filename = file.getName();
		
		Path realPath = tempLocation.resolve(path);
		Path realPathMin = tempMinLocation.resolve(path);
		byte[] data = null;
		byte[] dataMin = null;
		try {
			data = Files.readAllBytes(realPath);
			dataMin = Files.readAllBytes(realPathMin);
		} catch (IOException e) {
			throw e;
		}
		
		ImageUser picture = new ImageUser(filename, data, dataMin);
		
		return imgUsrRepository.save(picture);
	}


	@Override
	public ImageUser getUserOne(Long id) {
		
		ImageUser picture = imgUsrRepository.getOne(id);
		Hibernate.initialize(picture.getData());
		return picture;		
	}
}
