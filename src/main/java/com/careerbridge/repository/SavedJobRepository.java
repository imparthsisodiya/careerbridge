package com.careerbridge.repository;

import com.careerbridge.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {

    boolean existsByUserEmailAndJobId(String userEmail, Long jobId);

    @Transactional
    void deleteByUserEmailAndJobId(String userEmail, Long jobId);

    List<SavedJob> findByUserEmail(String userEmail);
}
