package hseneca.personal_website.service;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.model.request.CreateContactRequest;
import hseneca.personal_website.model.request.UpdateContactRequest;
import hseneca.personal_website.model.response.ContactResponse;
import hseneca.personal_website.repository.ContactRepository;
import hseneca.personal_website.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService implements ContactServiceImpl {
    @Autowired
    private ContactRepository contactRepository;
    //    @Qualifier("conversionService")
    @Autowired
    private ConversionService conversionService;

    public ContactResponse createContact(CreateContactRequest createContactRequest) {
        Contact contact = createContactRequest.toContact();
        contactRepository.save(contact);
        return ContactResponse.fromCotact(contact);
    }

    public ContactResponse updateContact(Long id,UpdateContactRequest updateContactRequest) {
        Contact contact = contactRepository.findById(id).orElseThrow(()->new RuntimeException("Contact not found"));

        contact.setFacebook(contact.getFacebook());
        contact.setInstagram(contact.getInstagram());
        contact.setGithub(contact.getGithub());
        contact.setLinkedIn(contact.getLinkedIn());
        contact.setPhoneNumber(contact.getPhoneNumber());
        
        contactRepository.save(contact);
        return ContactResponse.fromCotact(contact);
    }


    public Optional<Contact> getContact(Long id) {

        return contactRepository.findById(id);
    }

    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
