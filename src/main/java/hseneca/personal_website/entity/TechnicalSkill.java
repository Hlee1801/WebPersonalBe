package hseneca.personal_website.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TechnicalSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer techSkillId;
    private String techSkillName;
    private String type;
}
