package hseneca.personal_website.repository;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.model.request.CreateContactRequest;
import hseneca.personal_website.model.response.ContactResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByIdIn(List<Contact> contacts);
}
