package hseneca.personal_website.model.request;

import hseneca.personal_website.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    private String userName;
    private Integer age;
    private String school;

    private List<Contact> contacts;
    private List<Long> technicalSkills;

}
