package com.careerbridge.repository;

import com.careerbridge.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStudentEmail(String studentEmail);
}
