package hseneca.personal_website.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserDTO extends BaseDTO{
    private String username;
    private String password;
    private Integer age;
    private String school;
}
