package com.feritenesguzel.contacts.controller;

import java.util.List;

import com.feritenesguzel.contacts.exceptionhandler.EntityNotFoundException;
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
    public ResponseEntity<List<Contact>> getAllContact() throws EntityNotFoundException {
        List<Contact> result = contactService.getAll();
        HttpStatus status = (result == null || result.size() == 0) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/contact")
    public ResponseEntity<Boolean> addContact(@RequestBody Contact contact) {
        Boolean result = contactService.addContact(contact);
        HttpStatus status = result ? HttpStatus.CREATED : HttpStatus.EXPECTATION_FAILED;
        return new ResponseEntity<>(result, status);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Boolean> deleteContact(@PathVariable Long id) {
        Boolean result = contactService.deleteContact(id);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) throws EntityNotFoundException {
        Contact result = contactService.getContact(id);
        HttpStatus status = (result != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result, status);
    }

    @PutMapping("/contact")
    public ResponseEntity<Boolean> updateContact(@RequestBody Contact contact) {
        Boolean result = contactService.updateContact(contact) != null;
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/contact/search/{param}")
    public ResponseEntity<List<Contact>> searchContact(@PathVariable String param) throws EntityNotFoundException {
        List<Contact> result = contactService.searchContact(param);
        HttpStatus status = (result == null || result.size() == 0) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(result, status);
    }

}