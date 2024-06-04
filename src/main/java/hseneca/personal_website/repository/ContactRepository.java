package hseneca.personal_website.repository;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.model.request.CreateContactRequest;
import hseneca.personal_website.model.response.ContactResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByIdIn(Contact contacts);
}
