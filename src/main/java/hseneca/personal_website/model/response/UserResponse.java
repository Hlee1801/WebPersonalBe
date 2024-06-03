package hseneca.personal_website.model.response;

import hseneca.personal_website.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private Integer age;
    private String school;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .age(user.getAge())
                .school(user.getSchool())
                .build();
    }
}
