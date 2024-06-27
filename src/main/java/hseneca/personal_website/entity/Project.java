package hseneca.personal_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "projects")
public class Project extends BaseEntity{

    private String projectName;
    private String projectDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
