package com.careerbridge.service;

import com.careerbridge.dto.EducationRequest;
import com.careerbridge.entity.Education;
import com.careerbridge.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public Education addEducation(EducationRequest request) {
        Education education = new Education();
        education.setCollegeName(request.getCollegeName());
        education.setDegree(request.getDegree());
        education.setFieldOfStudy(request.getFieldOfStudy());
        education.setStartYear(request.getStartYear());
        education.setEndYear(request.getEndYear());
        education.setGrade(request.getGrade());
        education.setStudentEmail(request.getStudentEmail());
        return educationRepository.save(education);
    }

    public List<Education> getEducationByStudent(String studentEmail) {
        return educationRepository.findByStudentEmail(studentEmail);
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}
