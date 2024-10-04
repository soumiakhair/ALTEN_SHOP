package com.product.trial.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.trial.master.entities.Contact;
import com.product.trial.master.model.ContactForm;
import com.product.trial.master.repository.ContactRepository;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {
	
	@Autowired
    private ContactRepository contactrepo;

    @PostMapping
    public Contact submitContactForm(@RequestBody ContactForm form) {
        System.out.println("Received contact form: " + form);
        Contact contact= new Contact(form.getEmail(),form.getMessage());
        contactrepo.save(contact);
        return contact;
    }
}
