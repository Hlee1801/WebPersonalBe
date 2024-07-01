package hseneca.personal_website.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgeGroupStatsDto {
    private String ageGroup;
    private int total;

    public static AgeGroupStatsDto from(AgeGroupStats ageGroupStats) {
        AgeGroupStatsDto ageGroupStatsDto = new AgeGroupStatsDto();

        ageGroupStatsDto.setAgeGroup(ageGroupStats.getAgeGroup());
        ageGroupStatsDto.setTotal(ageGroupStats.getTotal());
        return ageGroupStatsDto;
    }
}
