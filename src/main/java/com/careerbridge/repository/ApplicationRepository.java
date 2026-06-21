package com.careerbridge.repository;

import com.careerbridge.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApplicantEmail(String applicantEmail);

    @Query("SELECT COUNT(a) > 0 FROM Application a WHERE a.applicantEmail = :email AND a.job.id = :jobId")
    boolean existsByApplicantEmailAndJobId(@Param("email") String applicantEmail, @Param("jobId") Long jobId);

    @Query("SELECT a FROM Application a WHERE a.job.recruiterEmail = :email")
    List<Application> findByJobRecruiterEmail(@Param("email") String recruiterEmail);
}
