package com.feritenesguzel.contacts.service;

import com.feritenesguzel.contacts.exceptionhandler.EntityNotFoundException;
import com.feritenesguzel.contacts.model.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> getAll() throws EntityNotFoundException;

    Contact getContact(long id) throws EntityNotFoundException;

    List<Contact> getContactByName(String name) throws EntityNotFoundException;

    Boolean addContact(Contact contact);

    Contact updateContact(Contact contact);

    Boolean deleteContact(Long id);

    List<Contact> searchContact(String param) throws EntityNotFoundException;
}
