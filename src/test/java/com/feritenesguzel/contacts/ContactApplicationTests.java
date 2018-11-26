package com.feritenesguzel.contacts;

import com.feritenesguzel.contacts.model.Contact;
import com.feritenesguzel.contacts.repo.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@Transactional// Written to avoid being affected by the database.
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class ContactApplicationTests {

    @Autowired
    private ContactRepository contactsRepository;

    private static final String TEST_NAME = "TestName";
    private static final String TEST_NAME_UPDATED = "TestNameUpdated";
    private static final String TEST_NAME_PART = "est";

    @Test
    public void createContact()   {
        Contact contact = new Contact();
        contact.setName(TEST_NAME);
        Boolean result = contactsRepository.save(contact) != null;
        assertEquals(true, result);
    }

    @Test
    public void createAndGetContact()   {
        Contact contact = new Contact();
        contact.setName(TEST_NAME);
        Boolean result = contactsRepository.save(contact) != null;

        Contact contact1 = contactsRepository.getContactsByName(TEST_NAME).get(0);

        assertEquals(true, result);
        assertEquals(TEST_NAME, contact1.getName());
    }
    @Test
    public void createAndDeleteContact()   {
        Contact contact = new Contact();
        contact.setName(TEST_NAME);
        contactsRepository.save(contact) ;

        Contact contact1 = contactsRepository.getContactsByName(TEST_NAME).get(0);
        contactsRepository.delete(contact1);
        List<Contact> contactList = contactsRepository.getContactsByName(TEST_NAME);

        assertEquals(true,contactList.isEmpty() );
    }

    @Test
    public void createAndUpdateContact()   {
        Contact contact = new Contact();
        contact.setName(TEST_NAME);
        Boolean result = contactsRepository.save(contact) != null;

        Contact updated = contactsRepository.getContactsByName(TEST_NAME).get(0);
        updated.setName(TEST_NAME_UPDATED);
        contactsRepository.save(updated);

        assertEquals(true, result);
        assertEquals(TEST_NAME_UPDATED, updated.getName());
        assertNotEquals(TEST_NAME, updated.getName());
    }

    @Test
    public void createAndSearchContact()   {
        Contact contact = new Contact();
        contact.setName(TEST_NAME);
        Boolean result = contactsRepository.save(contact) != null;

        Contact contact1 = contactsRepository.search(TEST_NAME_PART).get(0);

        assertEquals(true, result);
        assertEquals(TEST_NAME, contact1.getName());
    }


}