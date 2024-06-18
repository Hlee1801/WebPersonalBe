package hseneca.personal_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.Queue;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "contacts")
public class Contact extends BaseEntity {
    private String github;
    private String instagram;
    private String facebook;
    private String linkedIn;
    private Integer phoneNumber;

    @OneToOne
    @JoinColumn(name ="user_id")
    private User user;


}
