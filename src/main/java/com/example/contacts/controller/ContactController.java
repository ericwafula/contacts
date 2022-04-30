package com.example.contacts.controller;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.dto.ContactResponseDto;
import com.example.contacts.entity.Contact;
import com.example.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contacts")
public class ContactController {
    private final ContactService contactService;

    @PostMapping("new")
    public ResponseEntity<ContactResponseDto> addContact(@RequestBody ContactDto contactDto) {
        contactService.addContact(contactDto);
        ContactResponseDto contactResponse = ContactResponseDto.builder()
                .message("contact added successfully")
                .build();
        return new ResponseEntity<>(contactResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> allContacts() {
        return new ResponseEntity<>(contactService.allContacts(), HttpStatus.OK);
    }

    @GetMapping("id/{contactId}")
    public ResponseEntity<Contact> singleContact(@PathVariable("contactId") Long contactId) {
        return new ResponseEntity<>(contactService.singleContact(contactId), HttpStatus.OK);
    }

    @GetMapping("name/{contactName}")
    public ResponseEntity<List<Contact>> searchForContactUsingName(@PathVariable("contactName") String contactName) {
        return new ResponseEntity<>(contactService.searchForContactUsingName(contactName), HttpStatus.OK);
    }

    @PutMapping("id/{contactId}")
    public ResponseEntity<ContactResponseDto> updateContact(@PathVariable("contactId") Long contactId, @RequestBody ContactDto contactDto) {
        contactService.updateContact(contactId, contactDto);
        ContactResponseDto contactResponse = ContactResponseDto.builder()
                .message("contact updated successfully")
                .build();
        return new ResponseEntity<>(contactResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("id/{contactId}")
    public ResponseEntity<ContactResponseDto> deleteContact(@PathVariable("contactId") Long contactId) {
        contactService.deleteContact(contactId);
        ContactResponseDto contactResponse = ContactResponseDto.builder()
                .message("contact deleted successfully")
                .build();
        return new ResponseEntity<>(contactResponse, HttpStatus.ACCEPTED);
    }
}
