package hseneca.personal_website.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Entity(name = "projects")
@Data
@SuperBuilder
public class Project extends BaseEntity{

    private String projectName;
    private String projectDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
