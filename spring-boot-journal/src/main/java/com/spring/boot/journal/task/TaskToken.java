package com.spring.boot.journal.task;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.journal.repository.PasswordResetRepository;
import com.spring.boot.journal.repository.VerificationTokenRepository;

@Service
@Transactional
public class TaskToken {

	 @Autowired
	    VerificationTokenRepository tokenRepository;

	    @Autowired
	    PasswordResetRepository passwordTokenRepository;

	    @Scheduled(cron = "${purge.cron.expression}")
	    public void purgeExpired() {

	        Date now = Date.from(Instant.now());

	       // passwordTokenRepository.deleteAllExpiredSince(now);
	        //tokenRepository.deleteAllExpiredSince(now);
	    }
}
