package com.spring.boot.journal.task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.UtilisateurRepository;

@Service
@Transactional
public class TaskUsers {
	
	@Autowired
	private UtilisateurRepository repository;
	
	@Scheduled(cron = "${purge.cron.users}")
    public void purgeExpired() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
        Date now =  cal.getTime();
        
        ArrayList<Utilisateur> utilisateursInactifs = new ArrayList<Utilisateur>(); 
        utilisateursInactifs.addAll(repository.findInactive(now));
        if(utilisateursInactifs.size() > 0){
        	for (Utilisateur utilisateur : utilisateursInactifs) {
				repository.delete(utilisateur);
			}
        }
        	
    }

}
