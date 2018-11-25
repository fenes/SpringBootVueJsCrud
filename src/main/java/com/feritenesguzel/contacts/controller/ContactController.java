package com.feritenesguzel.contacts.controller;

import java.util.List;

import com.feritenesguzel.contacts.model.Contact;
import com.feritenesguzel.contacts.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")//write url
@RequestMapping(value = "/api/v1", produces = "application/json")//mapping value and accept type
public class ContactController {

    @Autowired
    IContactService contactService;//service impl

    //endpoints;
    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> getAllContact() {
        return new ResponseEntity<>(contactService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.addContact(contact), HttpStatus.OK);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Boolean> deleteContact(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.deleteContact(id), HttpStatus.OK);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.getContact(id), HttpStatus.OK);
    }

    @PutMapping("/contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.updateContact(contact), HttpStatus.OK);

    }

    @GetMapping("/contact/search/{param}")
    public ResponseEntity<List<Contact>> searchContact(@PathVariable String param) {
        return new ResponseEntity<>(contactService.searchContact(param), HttpStatus.OK);
    }

}