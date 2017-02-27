package com.spring.boot.journal.task;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.journal.repository.UtilisateurRepository;

@Service
@Transactional
public class TaskUsers {
	
	@Autowired
	private UtilisateurRepository repository;
	
	@Scheduled(cron = "${purge.cron.expression}")
    public void purgeExpired() {

        Date now = Date.from(Instant.now());

        /*if(!repository.findInactive(now)){
        	
        	//repository.deleteNotActive(now);
        }*/
        	
    }

}
