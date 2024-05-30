package hseneca.personal_website.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Entity(name = "project_skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProjectSkill extends BaseEntity{
    private String techStackType;

}
