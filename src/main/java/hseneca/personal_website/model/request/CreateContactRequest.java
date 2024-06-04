package hseneca.personal_website.model.request;

import hseneca.personal_website.entity.Contact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateContactRequest {
    @NotBlank
    private String facebook;
    @NotBlank
    private String github;
    @NotBlank
    private String instagram;
    @NotBlank
    private String linkIn;
    @NotNull
    private Long phoneNumber;

    public Contact toContact(){
        return Contact.builder()
                .facebook(facebook)
                .github(github)
                .instagram(instagram)
                .linkedIn(linkIn)
                .phoneNumber(phoneNumber).build();
    }


}
