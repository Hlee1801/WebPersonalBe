package hseneca.personal_website.entity;

import hseneca.personal_website.enums.TechnicalSkillType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity(name = "technical_skill")
@Data
public class TechnicalSkill extends BaseEntity {

    private String techSkillName;
    @Enumerated(EnumType.STRING)
    private TechnicalSkillType techSkillType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
