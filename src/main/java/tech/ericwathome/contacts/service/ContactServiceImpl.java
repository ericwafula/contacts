package tech.ericwathome.contacts.service;

import tech.ericwathome.contacts.dto.ContactDto;
import tech.ericwathome.contacts.entity.Contact;
import tech.ericwathome.contacts.error.NoContactFoundException;
import tech.ericwathome.contacts.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public void addContact(ContactDto contactDto) {
        Contact contact = new Contact();
        if (!contactDto.getName().isBlank()) {
            contact.setName(contactDto.getName());
        }
        if (!contactDto.getPhoneNumber().isBlank()) {
            contact.setPhoneNumber(contactDto.getPhoneNumber());
        }
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> allContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact singleContact(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.orElseThrow(() -> new NoContactFoundException("NO_CONTACT_FOUND_WITH_ID: " + contactId));
        return contact.get();
    }

    @Override
    public List<Contact> searchForContactUsingName(String contactName) {
        return contactRepository.findContactsByNameContainingIgnoreCase(contactName);
    }

    @Override
    @Transactional
    public void updateContact(Long contactId, ContactDto contactDto) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.orElseThrow(() -> new NoContactFoundException("NO_CONTACT_FOUND_WITH_ID: " + contactId));
        if (!contactDto.getName().isBlank()) {
            contact.get().setName(contactDto.getName());

        }
        if (!contactDto.getPhoneNumber().isBlank()) {
            contact.get().setPhoneNumber(contactDto.getPhoneNumber());
        }
        contactRepository.save(contact.get());
    }

    @Override
    @Transactional
    public void deleteContact(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.orElseThrow(() -> new NoContactFoundException("NO_CONTACT_FOUND_WITH_ID: "));
        contactRepository.deleteById(contactId);
    }
}
