package hseneca.personal_website.model.request;

import hseneca.personal_website.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProjectRequest {
    private Long id;
    private String projectName;
    private String projectDescription;

    public Project toProject(){
        return Project.builder()
                .id(id)
                .projectName(projectName)
                .projectDescription(projectDescription)
                .build();
    }
    
}
