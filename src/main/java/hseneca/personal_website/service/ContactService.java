package hseneca.personal_website.service;

import hseneca.personal_website.dto.ContactDTO;
import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.repository.ContactRepository;
import hseneca.personal_website.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService implements ContactServiceImpl {
    @Autowired
    private ContactRepository contactRepository;
//    @Qualifier("conversionService")
    @Autowired
    private ConversionService conversionService;

    public List<ContactDTO> findAll() {
        return contactRepository.findAll().stream()
                .map(this::convertToContactDTO)
                .collect(Collectors.toList());
    }

    public ContactDTO create(ContactDTO contactDTO) {
        Contact contact = convertToContact(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        return convertToContactDTO(savedContact);
    }

    public ContactDTO update(ContactDTO contactDTO) {
        Optional<Contact> optionalContact = contactRepository.findById(contactDTO.getContactId());
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setFacebook(contactDTO.getFacebook());
            contact.setInstagram(contactDTO.getInstagram());
            contact.setGithub(contactDTO.getGithub());
            contact.setLinkedIn(contactDTO.getLinkedIn());
            Contact updatedContact = contactRepository.save(contact);
            return convertToContactDTO(updatedContact);
        }
        return null; // Or throw an exception if contact not found
    }

    public boolean delete(Integer contactId) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (optionalContact.isPresent()) {
            contactRepository.delete(optionalContact.get());
            return true; // Successfully deleted
        }
        return false; // Contact not found
    }

    private ContactDTO convertToContactDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setContactId(contact.getContactId());
        contactDTO.setFacebook(contact.getFacebook());
        contactDTO.setInstagram(contact.getInstagram());
        contactDTO.setGithub(contact.getGithub());
        contactDTO.setLinkedIn(contact.getLinkedIn());
        return contactDTO;
    }

    private Contact convertToContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setContactId(contactDTO.getContactId());
        contact.setFacebook(contactDTO.getFacebook());
        contact.setInstagram(contactDTO.getInstagram());
        contact.setGithub(contactDTO.getGithub());
        contact.setLinkedIn(contactDTO.getLinkedIn());
        return contact;
    }
}

