package com.feritenesguzel.contacts.controller;

import java.util.List;

import com.feritenesguzel.contacts.model.Contact;
import com.feritenesguzel.contacts.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ContactController {

    @Autowired
    IContactService contactsService;

    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> getAllContact() {
        return new ResponseEntity<>(contactsService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactsService.addContact(contact), HttpStatus.OK);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Boolean> deleteContact(@PathVariable Long id) {
        return new ResponseEntity<>(contactsService.deleteContact(id), HttpStatus.OK);
    }

    @PutMapping("/contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactsService.addContact(contact), HttpStatus.OK);
    }

}