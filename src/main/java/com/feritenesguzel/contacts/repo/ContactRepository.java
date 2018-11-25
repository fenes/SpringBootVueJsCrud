package com.feritenesguzel.contacts.repo;

import com.feritenesguzel.contacts.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT co FROM Contact as co LEFT JOIN co.addressList addr WHERE lower(co.name) like %?1% or lower(co.surname) like %?1% or lower(addr.key) like %?1% or lower(addr.value) like %?1% ")
    List<Contact> search(String userName);
}
