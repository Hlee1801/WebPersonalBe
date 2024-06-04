package hseneca.personal_website.model.request;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.Project;
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
    private String password;
    private Integer age;
    private String school;

    private List<Project> projects;
    private List<Long> technicalSkills;
    private Contact contact;

}
