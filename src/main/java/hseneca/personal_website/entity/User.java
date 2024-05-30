package hseneca.personal_website.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.scheduling.config.Task;

import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "users")
@Data
@SuperBuilder
public class User extends BaseEntity{
    private String userName;
    private Integer age;
    private String school;

    @OneToOne(mappedBy = "user")
    private Contact contact;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;

    @OneToMany(mappedBy = "user")
    private List<TechnicalSkill> technicalSkills;

}
