package hseneca.personal_website.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProjectRequest {
    private Long id;
    private String projectName;
    private String projectDescription;
}
