package com.product.trial.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.trial.master.entities.Contact;


public interface ContactRepository  extends JpaRepository<Contact, Long> {

}
