package com.feritenesguzel.contacts.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Phone entity related to 'Contact' obj.(Many to one)
 */


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//added because json serialazition ignores infinite loop in objects.
@Table(name = "phone")
public class Phone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Key")
    private String key;

    @Column(name = "Value")
    private String value;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Contact_id", referencedColumnName = "id")
    private Contact contact;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
