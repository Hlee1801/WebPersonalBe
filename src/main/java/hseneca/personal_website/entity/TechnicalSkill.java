package hseneca.personal_website.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity(name = "technical_skill")
@Data
public class TechnicalSkill extends BaseEntity {

    private String techSkillName;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
