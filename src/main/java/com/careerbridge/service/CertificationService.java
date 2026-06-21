package com.careerbridge.service;

import com.careerbridge.dto.CertificationRequest;
import com.careerbridge.entity.Certification;
import com.careerbridge.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    public Certification addCertification(CertificationRequest request) {
        Certification certification = new Certification();
        certification.setTitle(request.getTitle());
        certification.setIssuer(request.getIssuer());
        certification.setIssueDate(request.getIssueDate());
        certification.setCredentialId(request.getCredentialId());
        certification.setCredentialUrl(request.getCredentialUrl());
        certification.setStudentEmail(request.getStudentEmail());
        return certificationRepository.save(certification);
    }

    public List<Certification> getCertificationsByStudent(String studentEmail) {
        return certificationRepository.findByStudentEmail(studentEmail);
    }

    public void deleteCertification(Long id) {
        certificationRepository.deleteById(id);
    }
}
