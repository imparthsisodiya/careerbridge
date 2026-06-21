package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.CertificationRequest;
import com.careerbridge.entity.Certification;
import com.careerbridge.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
@CrossOrigin("*")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @PostMapping
    public ApiResponse<Certification> addCertification(@RequestBody CertificationRequest request) {
        return new ApiResponse<>(true, "Certification added successfully",
                certificationService.addCertification(request));
    }

    @GetMapping
    public ApiResponse<List<Certification>> getCertifications(@RequestParam String studentEmail) {
        return new ApiResponse<>(true, "Certifications fetched",
                certificationService.getCertificationsByStudent(studentEmail));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCertification(@PathVariable Long id) {
        certificationService.deleteCertification(id);
        return new ApiResponse<>(true, "Certification deleted", "Deleted");
    }
}
