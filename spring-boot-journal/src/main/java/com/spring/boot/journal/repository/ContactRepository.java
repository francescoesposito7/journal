package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.Contact;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
