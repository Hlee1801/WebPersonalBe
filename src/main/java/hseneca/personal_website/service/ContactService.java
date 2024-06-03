package hseneca.personal_website.service;

import hseneca.personal_website.dto.ContactDTO;
import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.repository.ContactRepository;
import hseneca.personal_website.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

}

