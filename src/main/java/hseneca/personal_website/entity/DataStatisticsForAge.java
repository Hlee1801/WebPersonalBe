package hseneca.personal_website.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "data_statics_for_age")
public class DataStatisticsForAge {
    @Id
    private String agerange;
    private Integer usercount;

}
