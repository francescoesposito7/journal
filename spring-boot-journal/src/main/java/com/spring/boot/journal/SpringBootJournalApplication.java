package com.spring.boot.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootJournalApplication{

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}
}
