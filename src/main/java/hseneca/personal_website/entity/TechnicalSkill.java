package hseneca.personal_website.entity;

import ch.qos.logback.classic.spi.LoggingEventVO;
import hseneca.personal_website.enums.TechnicalSkillType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "technical_skill")
@SuperBuilder
public class TechnicalSkill extends BaseEntity {

    private String techSkillName;
    @Enumerated(EnumType.STRING)
    private TechnicalSkillType techSkillType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
