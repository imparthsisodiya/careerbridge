package com.careerbridge.repository;

import com.careerbridge.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByStudentEmail(String studentEmail);
}
