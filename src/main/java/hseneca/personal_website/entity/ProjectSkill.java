package hseneca.personal_website.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class ProjectSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectSkillId;
    private String techStackType;

    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;
}
