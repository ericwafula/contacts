package tech.ericwathome.contacts.repository;

import tech.ericwathome.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findContactsByNameContainingIgnoreCase(String name);
}
