package com.feritenesguzel.contacts.service;

import com.feritenesguzel.contacts.model.Adress;
import com.feritenesguzel.contacts.model.Contact;
import com.feritenesguzel.contacts.model.Phone;
import com.feritenesguzel.contacts.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {

    @Autowired
    ContactRepository contactsRepository;

    @Override
    public List<Contact> getAll() {
        return contactsRepository.findAll();
    }

    @Override
    public Contact getContact(long id) {
        return contactsRepository.findById(id).orElse(null);
    }

    @Override
    public Contact addContact(Contact contact) {
        if (contact.getPhoneList() != null && contact.getPhoneList().size() != 0)
            for (Phone phone : contact.getPhoneList()) {
                phone.setContact(contact);
            }

        if (contact.getAddressList() != null && contact.getAddressList().size() != 0)
            for (Adress adress : contact.getAddressList()) {
                adress.setContact(contact);
            }

        return contactsRepository.save(contact);
    }

    @Override
    public Boolean deleteContact(Long id) {
        try {
            contactsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}