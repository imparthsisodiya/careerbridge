package com.careerbridge.service;

import com.careerbridge.dto.ProjectRequest;
import com.careerbridge.entity.Project;
import com.careerbridge.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project addProject(ProjectRequest request) {
        Project project = new Project();
        project.setTitle(request.getTitle());
        project.setTechStack(request.getTechStack());
        project.setGithubLink(request.getGithubLink());
        project.setLiveLink(request.getLiveLink());
        project.setDescription(request.getDescription());
        project.setStudentEmail(request.getStudentEmail());
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByStudent(String studentEmail) {
        return projectRepository.findByStudentEmail(studentEmail);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
