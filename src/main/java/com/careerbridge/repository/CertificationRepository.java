package com.careerbridge.repository;

import com.careerbridge.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findByStudentEmail(String studentEmail);
}
