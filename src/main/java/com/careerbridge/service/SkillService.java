package com.careerbridge.service;

import com.careerbridge.dto.SkillRequest;
import com.careerbridge.entity.Skill;
import com.careerbridge.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill addSkill(SkillRequest request) {
        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setLevel(request.getLevel());
        skill.setStudentEmail(request.getStudentEmail());
        return skillRepository.save(skill);
    }

    public List<Skill> getSkillsByStudent(String studentEmail) {
        return skillRepository.findByStudentEmail(studentEmail);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
