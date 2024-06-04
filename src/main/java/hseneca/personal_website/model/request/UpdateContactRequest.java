package hseneca.personal_website.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateContactRequest {
    private String facebook;
    private String github;
    private String instagram;
    private String linkIn;
    private Long phoneNumber;
}
