package com.feritenesguzel.contacts.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.*;
import javax.persistence.*;
import java.util.Set;
/**
 * Phone entity related to 'Contact' obj.(Many to one)
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//added because json serialazition ignores infinite loop in objects.
@Table(name = "contact")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Birthday")
    private String birthday;

    @Column(name = "WorkInfo")
    private String workInfo;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "contact"
    )
    private Set<Phone> phoneList;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "contact"
    )
    private Set<Address> addressList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surName) {
        this.surname = surName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(String workInfo) {
        this.workInfo = workInfo;
    }

    public Set<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(Set<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public Set<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Set<Address> adressList) {
        this.addressList = adressList;
    }

    @Override
    public String toString() {
        return "Contact [id=" + id + ", data=" + name + "]";
    }

}
