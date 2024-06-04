package hseneca.personal_website.controller;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.model.request.CreateContactRequest;
import hseneca.personal_website.model.request.UpdateContactRequest;
import hseneca.personal_website.model.response.ContactResponse;
import hseneca.personal_website.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")

public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping
    public ContactResponse createContact(@RequestBody @Valid CreateContactRequest createContactRequest) {
        return contactService.createContact(createContactRequest);
    }

    @PutMapping
    public ContactResponse updateContact(@PathVariable Long id ,@RequestBody @Valid UpdateContactRequest updateContactRequest) {
        return contactService.updateContact(id,updateContactRequest);
    }

    @GetMapping
    public Optional<Contact> getAllContacts(@PathVariable Long id) {
        return contactService.getContact(id);
    }

    @DeleteMapping
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContactById(id);
    }

}
