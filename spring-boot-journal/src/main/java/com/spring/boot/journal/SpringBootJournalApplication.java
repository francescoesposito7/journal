package com.spring.boot.journal;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.boot.journal.entities.ImageUser;
import com.spring.boot.journal.repository.ImageUserRepository;

@SpringBootApplication
public class SpringBootJournalApplication implements CommandLineRunner {
	
	@Autowired
	private ImageUserRepository imageRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		/*ImageUser imageUser = new ImageUser();
		
		File file = new File("C:\\Users\\94010-24-02\\git\\journal\\spring-boot-journal\\src\\main\\resources\\static\\img\\avatar_defaut.png");
		
		byte[] data = IOUtils.toByteArray(new FileInputStream(file));
		
		imageUser.setData(data);
		
		imageUser.setName("avatar_defaut.png");
		
		imageRepository.save(imageUser);*/
		
	}
}
