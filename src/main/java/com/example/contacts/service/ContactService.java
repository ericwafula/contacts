package com.example.contacts.service;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.entity.Contact;

import java.util.List;

public interface ContactService {
    void addContact(ContactDto contactDto);

    List<Contact> allContacts();

    Contact singleContact(Long contactId);

    List<Contact> searchForContactUsingName(String contactName);

    void updateContact(Long contactId, ContactDto contactDto);

    void deleteContact(Long contactId);
}
