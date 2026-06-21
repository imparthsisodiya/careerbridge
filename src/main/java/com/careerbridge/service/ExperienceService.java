package com.careerbridge.service;

import com.careerbridge.dto.ExperienceRequest;
import com.careerbridge.entity.Experience;
import com.careerbridge.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public Experience addExperience(ExperienceRequest request) {
        Experience experience = new Experience();
        experience.setCompanyName(request.getCompanyName());
        experience.setRole(request.getRole());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setDescription(request.getDescription());
        experience.setStudentEmail(request.getStudentEmail());
        return experienceRepository.save(experience);
    }

    public List<Experience> getExperienceByStudent(String studentEmail) {
        return experienceRepository.findByStudentEmail(studentEmail);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }
}
