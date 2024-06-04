package hseneca.personal_website.model.response;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {
    private Long id;
    private String facebook;
    private String github;
    private String instagram;
    private String linkedIn;
    private Long phone_number;

    public static ContactResponse fromCotact(Contact contact) {
            return ContactResponse.builder()
                    .id(contact.getId())
                    .facebook(contact.getFacebook())
                    .github(contact.getGithub())
                    .instagram(contact.getInstagram())
                    .linkedIn(contact.getLinkedIn())
                    .phone_number(contact.getPhoneNumber())
                    .build();
    }
}
