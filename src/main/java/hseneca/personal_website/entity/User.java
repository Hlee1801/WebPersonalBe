package hseneca.personal_website.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.scheduling.config.Task;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity{
    private String userName;
    private String email;
    private String password;
    private Integer age;
    private String school;

    @OneToOne(mappedBy = "user")
    private Contact contact;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;

    @OneToMany(mappedBy = "user")
    private List<TechnicalSkill> technicalSkills;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;

    public User() {

    }

    public User(String username, String password, ArrayList<Object> objects) {
    }
}
