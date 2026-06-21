
package com.careerbridge.repository;

import com.careerbridge.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByStudentEmail(String studentEmail);
}