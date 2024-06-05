package hseneca.personal_website.model.request;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.Project;
import hseneca.personal_website.entity.TechnicalSkill;
import hseneca.personal_website.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    @NotBlank
    private String userName;
    @NotNull
    private Integer age;
    @NotBlank
    private String school;

    private List<Contact> contacts;
    private List<Long> technicalSkills;
    private List<Project> projects;

    public User toUser() {
        return User.builder()
                .userName(userName)
                .age(age)
                .school(school)
                .build();
        }

}
