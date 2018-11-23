package com.feritenesguzel.contacts.service;

import com.feritenesguzel.contacts.model.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> getAll();
    Contact getContact(long id);
    Contact addContact(Contact contact);
    Boolean deleteContact(Long id);
}
