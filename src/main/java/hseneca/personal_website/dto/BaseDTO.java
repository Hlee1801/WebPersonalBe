package hseneca.personal_website.dto;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
public abstract class BaseDTO {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
