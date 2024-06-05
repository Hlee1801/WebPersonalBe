package hseneca.personal_website.service;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.Project;
import hseneca.personal_website.model.request.CreateProjectRequest;
import hseneca.personal_website.model.request.UpdateProjectRequest;
import hseneca.personal_website.model.response.ProjectResponse;
import hseneca.personal_website.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectResponse createProject(CreateProjectRequest createProjectRequest) {
        Project project = createProjectRequest.toProject();
        projectRepository.save(project);
        return ProjectResponse.fromProject(project);
    }

    public ProjectResponse updateProject(Long id, UpdateProjectRequest updateProjectRequest) {
        Project project = projectRepository.findById(id).orElseThrow(()->new RuntimeException("Project not found"));

        project.setId(updateProjectRequest.getId());
        project.setProjectName(updateProjectRequest.getProjectName());
        project.setProjectDescription(updateProjectRequest.getProjectDescription());

        Project saveUser = projectRepository.save(project);
        return ProjectResponse.fromProject(project);
    }

    public Optional<Project> getProject(Long id) {

        return projectRepository.findById(id);
    }

    public void deleteContactById(Long id) {
        projectRepository.deleteById(id);
    }
}
