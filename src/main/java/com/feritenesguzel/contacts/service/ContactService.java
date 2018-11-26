package com.feritenesguzel.contacts.service;

import com.feritenesguzel.contacts.exceptionhandler.EntityNotFoundException;
import com.feritenesguzel.contacts.model.Address;
import com.feritenesguzel.contacts.model.Contact;
import com.feritenesguzel.contacts.model.Phone;
import com.feritenesguzel.contacts.repo.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {
    private final Logger logger = LoggerFactory.getLogger(ContactService.class);
    @Autowired
    ContactRepository contactsRepository;

    @Override
    public List<Contact> getAll() throws EntityNotFoundException {
        try {
            return contactsRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Cacheable("Contact")//redis add
    @Override
    public Contact getContact(long id) throws EntityNotFoundException {
        return contactsRepository.findById(id).orElse(null);

    }

    @Override
    public List<Contact> getContactByName(String name) throws EntityNotFoundException {
        return contactsRepository.getContactsByName(name);
    }

    @Override
    public Boolean addContact(Contact contact) {
        try {
            contactsRepository.save(FillAddressAndPhoneListSContactObj(contact));
            logger.debug("Saved person [{}]", contact.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @CachePut(key = "#contact.id", value = "Contact")//redis update
    @Override
    public Contact updateContact(Contact contact) {
        try {
            contactsRepository.save(FillAddressAndPhoneListSContactObj(contact));
            logger.debug("Updated person [{}]", contact.getId());
            return contact  ;
        } catch (Exception e) {
            return null;
        }
    }

    @CacheEvict("Contact")//redis delete Contact index with id
    @Override
    public Boolean deleteContact(Long id) {
        try {
            contactsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Contact> searchContact(String param) throws EntityNotFoundException {
        try {
            return contactsRepository.search(param.toLowerCase());
        } catch (Exception e) {
            return null;
        }
    }

    private Contact FillAddressAndPhoneListSContactObj(Contact contact) {//Create relation with phoneList and AddressList objects to Contact obj.
        if (contact.getPhoneList() != null && contact.getPhoneList().size() != 0)
            for (Phone phone : contact.getPhoneList()) {
                phone.setContact(contact);
            }

        if (contact.getAddressList() != null && contact.getAddressList().size() != 0)
            for (Address adress : contact.getAddressList()) {
                adress.setContact(contact);
            }
        return contact;
    }
}