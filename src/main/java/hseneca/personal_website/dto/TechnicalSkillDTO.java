package hseneca.personal_website.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TechnicalSkillDTO {
    private Integer techSkillId;
    private String techSkillName;
    private String type;

}
